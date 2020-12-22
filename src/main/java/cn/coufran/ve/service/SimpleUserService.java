package cn.coufran.ve.service;

import cn.coufran.ve.data.TokenRepository;
import cn.coufran.ve.data.UserRepository;
import cn.coufran.ve.model.Token;
import cn.coufran.ve.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SimpleUserService implements UserService {

    @Resource
    private TokenRepository tokenRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public User get(String tokenString) {
        Token token = tokenRepository.selectById(tokenString);
        if(token == null) {
            throw new IllegalArgumentException("token不可用");
        }
        Integer userId = token.getUserId();
        User user = userRepository.selectById(userId);
        return user;
    }
}
