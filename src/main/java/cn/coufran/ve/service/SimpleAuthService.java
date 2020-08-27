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

@Service
public class SimpleAuthService implements AuthService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private TokenRepository tokenRepository;
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
        // 生成token，保存
        String tokenString = UUID.randomUUID().toString().replaceAll("-", "");
        long expire = System.currentTimeMillis() + 1000 * 60 * 30; // 有效期30min
        Token token = new Token(tokenString, user.getId(), expire);
        tokenRepository.insert(token);
        return tokenString;
    }

    @Override
    public boolean isLogin(String tokenString) {
        Token token = tokenRepository.selectById(tokenString);
        if(token == null) {
            return false;
        }
        long expire = token.getExpire();
        if(System.currentTimeMillis() > expire) {
            return false;
        }
        return true;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void clearToken() {
        LambdaUpdateWrapper<Token> wrapper = new UpdateWrapper<Token>().lambda()
                .lt(Token::getExpire, System.currentTimeMillis());
        tokenRepository.delete(wrapper);
    }

}
