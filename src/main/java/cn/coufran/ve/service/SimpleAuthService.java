package cn.coufran.ve.service;

import cn.coufran.springboot.starter.api.exception.ServiceException;
import cn.coufran.ve.data.TokenRepository;
import cn.coufran.ve.data.UserRepository;
import cn.coufran.ve.model.Token;
import cn.coufran.ve.model.User;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    public String login(User user) {
        // 验证用户信息
        String encryptPassword = secretService.md5(user.getPassword(), user.getUsername());
        user = userRepository.selectByUsernameAndPassword(user.getUsername(), encryptPassword);
        if(user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        // 生成token（随机UUID），保存
        String tokenString = UUID.randomUUID().toString().replaceAll("-", "");
        long expire = System.currentTimeMillis() + 1000 * 60 * 30; // 有效期30min
        Token token = new Token(tokenString, user.getId(), expire);
        tokenRepository.insert(token);
        return tokenString;
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
