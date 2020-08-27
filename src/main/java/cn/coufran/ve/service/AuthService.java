package cn.coufran.ve.service;

import cn.coufran.ve.model.User;

public interface AuthService {
    String login(User user);

    boolean isLogin(String token);
}
