package cn.coufran.ve.service;

import cn.coufran.ve.data.AccountRepository;
import cn.coufran.ve.model.Account;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SimpleAccountService implements AccountService {
    @Resource
    private AccountRepository accountRepository;

    @Override
    public List<Account> list() {
        return accountRepository.selectList(new QueryWrapper<>());
    }
}
