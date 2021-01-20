package cn.coufran.ve.api;

import cn.coufran.ve.api.vo.ClientTokenVo;
import cn.coufran.ve.interceptor.Public;
import cn.coufran.ve.model.User;
import cn.coufran.ve.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 权限类接口
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@RestController
@RequestMapping("/web/auth")
@CrossOrigin
public class AuthController {

    /**
     * 权限业务组件
     */
    @Resource
    private AuthService authService;

    /**
     * 登录并返回accessToken和refreshToken。</br>
     * 多个Token可同时生效，所以这个本系统支持同时多端在线，每端使用各自不同的Token，互不影响。
     * @param user 登录参数，包含用户名和密码两部分
     * @return accessToken和refreshToken
     */
    @PostMapping("/login")
    @Public
    public ClientTokenVo login(@RequestBody User user) {
        return authService.login(user);
    }

    /**
     * 使用refreshToken交换accessToken，获取新的Token后，原来的refreshToken立刻失效，原来的accessToken依然有效
     * @param refreshToken refreshToken
     * @return 新的accessToken和refreshToken
     */
    @PostMapping("/refresh")
    @Public
    public ClientTokenVo refresh(@RequestParam("refreshToken") String refreshToken) {
        return authService.refresh(refreshToken);
    }

    /**
     * 登出
     * @param token token
     */
    @PostMapping("/logout")
    public Boolean logout(@RequestHeader("Token") String token) {
        return authService.logout(token);
    }


}
