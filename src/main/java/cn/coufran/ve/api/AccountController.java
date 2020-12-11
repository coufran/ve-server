package cn.coufran.ve.api;

import cn.coufran.ve.model.Account;
import cn.coufran.ve.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/web/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/list")
    public List<Account> list() {
        return accountService.list();
    }
}
