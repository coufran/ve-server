package cn.coufran.ve.service;

import cn.coufran.ve.api.vo.ClientTokenVo;
import cn.coufran.ve.model.User;

/**
 * 权限控制组件
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
public interface AuthService {
    /**
     * 登录并获取Token，Token 30min过期，不支持续期，如需使用超过30min，需要再次调用本方法获取新的Token。
     * 同一用户可同时存在多个Token，意味着多端可以同时在线，多个Token互相独立。
     * @param user 用户，包含用户名和加密前密码
     * @return token
     * @throws cn.coufran.springboot.starter.api.exception.ServiceException 用户名或密码错误
     */
    ClientTokenVo login(User user);

    /**
     * 判定Token是否已登录，如果Token为空、错误或已过期，返回false
     * @param token Token，可为空
     * @return token有效返回true，否则返回false
     */
    boolean isLogin(String token);

    /**
     * 登出
     * @param token token
     * @return 登出是否成功
     */
    boolean logout(String token);

    /**
     * 使用refreshToken交换accessToken，获取新的Token后，原来的refreshToken立刻失效，原来的accessToken在30s内依然有效
     * @param refreshToken refreshToken
     * @return 新的accessToken和refreshToken
     */
    ClientTokenVo refresh(String refreshToken);
}
