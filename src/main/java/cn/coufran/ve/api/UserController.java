package cn.coufran.ve.api;

import cn.coufran.ve.model.User;
import cn.coufran.ve.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/web/user")
public class UserController {
    @Resource
    public UserService userService;

    @RequestMapping("/get")
    public User get(@RequestHeader("Token") String token) {
        return userService.get(token);
    }
}
