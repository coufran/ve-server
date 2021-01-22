package cn.coufran.ve.service;

import cn.coufran.springboot.starter.api.exception.ServiceException;
import cn.coufran.ve.api.vo.ClientTokenVo;
import cn.coufran.ve.data.TokenRepository;
import cn.coufran.ve.data.UserRepository;
import cn.coufran.ve.model.Token;
import cn.coufran.ve.model.User;
import cn.coufran.ve.util.TokenGenerator;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 权限组件基础实现
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class SimpleAuthService implements AuthService {
    /** access token 有效时间 */
    private static final int ACTIVE_MINUTES_ACCESS_TOKEN = 1;
    /** refresh token 有效时间 */
    private static final int ACTIVE_MINUTES_REFRESH_TOKEN = 48;

    /** 用户数据仓库 */
    @Resource
    private UserRepository userRepository;
    /** Token数据仓库 */
    @Resource
    private TokenRepository tokenRepository;
    /** 加解密组件 */
    @Resource
    private SecretService secretService;

    @Override
    @Transactional
    public ClientTokenVo login(User user) {
        // 验证用户信息
        String encryptPassword = secretService.md5(user.getPassword(), user.getUsername());
        user = userRepository.selectByUsernameAndPassword(user.getUsername(), encryptPassword);
        if(user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        // 生成access token，保存
        Token accessToken = TokenGenerator.generateToken(Token.Type.ACCESS, user, ACTIVE_MINUTES_ACCESS_TOKEN);
        tokenRepository.insert(accessToken);
        // 生成refresh token，保存
        Token refreshToken = TokenGenerator.generateToken(Token.Type.REFRESH, user, ACTIVE_MINUTES_REFRESH_TOKEN);
        tokenRepository.insert(refreshToken);
        return new ClientTokenVo(accessToken, refreshToken);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ClientTokenVo refresh(String refreshToken) {
        // 验证 refreshToken
        Token oldRefreshToken = tokenRepository.selectByIdForUpdate(refreshToken);
        if (oldRefreshToken == null  // 找不到token
                || oldRefreshToken.getType() != Token.Type.REFRESH // token 类型错误
                || oldRefreshToken.getExpire() < System.currentTimeMillis()) { // token 已过期
            throw new ServiceException("Refresh Token 无效");
        }

        // 生成新的Token
        Integer userId = oldRefreshToken.getUserId();
        Token newAccessToken = TokenGenerator.generateToken(Token.Type.ACCESS, userId, ACTIVE_MINUTES_ACCESS_TOKEN);
        Token newRefreshToken = TokenGenerator.generateToken(Token.Type.REFRESH, userId, ACTIVE_MINUTES_REFRESH_TOKEN);
        tokenRepository.insert(newAccessToken);
        tokenRepository.insert(newRefreshToken);
        // 删除旧的RefreshToken
        int i = tokenRepository.deleteById(refreshToken);
        System.out.println(refreshToken + " " + i);

        return new ClientTokenVo(newAccessToken, newRefreshToken);
    }

    @Override
    public boolean isLogin(String tokenString) {
        if(tokenString == null) {
            return false;
        }
        // 存在判定
        Token token = tokenRepository.selectById(tokenString);
        if(token == null) {
            return false;
        }
        // 类型判定
        Token.Type type = token.getType();
        if(type != Token.Type.ACCESS) {
            return false;
        }
        // 过期判定
        long expire = token.getExpire();
        if(System.currentTimeMillis() > expire) {
            return false;
        }
        return true;
    }

    @Override
    public boolean logout(String token) {
        tokenRepository.deleteById(token);
        return true;
    }

    /**
     * 定时任务，每分钟执行一次，清除已过期的Token
     */
    @Scheduled(cron = "0 * * * * ?")
    public void clearToken() {
        LambdaUpdateWrapper<Token> wrapper = new UpdateWrapper<Token>().lambda()
                .lt(Token::getExpire, System.currentTimeMillis());
        tokenRepository.delete(wrapper);
    }

}
