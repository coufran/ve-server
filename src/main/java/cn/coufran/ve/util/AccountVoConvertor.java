package cn.coufran.ve.util;

import cn.coufran.ve.api.vo.AccountVo;
import cn.coufran.ve.model.Account;
import cn.coufran.ve.model.Title;
import cn.coufran.ve.service.TitleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountVoConvertor implements PojoConvertor<AccountVo, Account> {
    @Resource
    private TitleService titleService;

    @Override
    public AccountVo from(Account account) {
        AccountVo accountVo = new AccountVo();
        accountVo.setId(account.getId());
        accountVo.setAmount(account.getAmount());
        accountVo.setName(account.getName());
        Title title = titleService.getById(account.getTitleId());
        accountVo.setTitle(title);
        return accountVo;
    }

    @Override
    public AccountVo to(Account account) {
        return null;
    }
}
