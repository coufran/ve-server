package cn.coufran.ve.api;

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
     * 登录并返回本次登录的Token。Token默认超时时间是30min，暂不支持延期，需要使用超过30min时，需要再次登录。
     * 多个Token可同时生效，所以这个本系统支持同时多端在线，每端使用各自不同的Token，互不影响。
     * @param user 登录参数，包含用户名和密码两部分
     * @return 登录结果，data中包含本次登录的Token。
     */
    @PostMapping("/login")
    @Public
    public String login(@RequestBody User user) {
        return authService.login(user);
    }

    /**
     * 验证指定Token是否登录
     * @param token 指定Token，可为空，为空是返回未登录。Token参数不建议为空，会浪费服务器资源，建议在前端通过判空操作校验
     * @return 是否登录
     */
    @RequestMapping("/isLogin")
    @Public
    public Boolean isLogin(@RequestHeader(value = "Token", required = false) String token) {
        return authService.isLogin(token);
    }

}
