package cn.coufran.ve.api;

import cn.coufran.springboot.starter.api.Result;
import cn.coufran.ve.interceptor.Public;
import cn.coufran.ve.model.User;
import cn.coufran.ve.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/web/auth")
@CrossOrigin
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/login")
    @Public
    public Result login(@RequestBody User user) {
        String token = authService.login(user);
        Result result = Result.ok();
        result.setData(token);
        return result;
    }

    @RequestMapping("/isLogin")
    @Public
    public Result isLogin(@RequestHeader(value = "Token", required = false) String token) {
        boolean isLogin = authService.isLogin(token);
        return Result.ok(isLogin);
    }

}
