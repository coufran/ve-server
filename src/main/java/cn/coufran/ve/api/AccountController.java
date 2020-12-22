package cn.coufran.ve.api;

import cn.coufran.ve.api.vo.AccountVo;
import cn.coufran.ve.model.Account;
import cn.coufran.ve.service.AccountService;
import cn.coufran.ve.util.AccountVoConvertor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/web/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    @Resource
    private AccountVoConvertor accountVoConvertor;

    @RequestMapping("/list")
    public List<AccountVo> list() {
        return accountService.list().stream()
                .map(accountVoConvertor::from)
                .collect(Collectors.toList());
    }
}
