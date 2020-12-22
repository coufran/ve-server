package cn.coufran.ve.service;

import cn.coufran.ve.model.User;

public interface UserService {
    User get(String token);
}
