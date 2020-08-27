package cn.coufran.ve.api;

import cn.coufran.springboot.starter.api.Result;
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
    public Result login(@RequestBody User user) {
        String token = authService.login(user);
        Result result = Result.ok();
        result.setData(token);
        return result;
    }

    @RequestMapping("/isLogin")
    public Result isLogin(@RequestHeader("Token") String token) {
        boolean isLogin = authService.isLogin(token);
        return Result.ok(isLogin);
    }

}
