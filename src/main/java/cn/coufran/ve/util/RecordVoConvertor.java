package cn.coufran.ve.util;

import cn.coufran.ve.api.vo.RecordVo;
import cn.coufran.ve.model.Account;
import cn.coufran.ve.model.Record;
import cn.coufran.ve.service.AccountService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RecordVoConvertor implements PojoConvertor<RecordVo, Record> {

    @Resource
    private AccountService accountService;
    @Resource
    private AccountVoConvertor accountVoConvertor;

    @Override
    public RecordVo from(Record record) {
        RecordVo recordVo = new RecordVo();
        recordVo.setId(record.getId());
        recordVo.setAmount(record.getAmount());
        recordVo.setGroupId(record.getGroupId());
        recordVo.setRemark(record.getRemark());
        recordVo.setTime(record.getTime());
        Account credit = accountService.getById(record.getCreditId());
        recordVo.setCredit(accountVoConvertor.from(credit));
        Account debit = accountService.getById(record.getDebitId());
        recordVo.setDebit(accountVoConvertor.from(debit));
        return recordVo;
    }

    @Override
    public RecordVo to(Record record) {
        return null;
    }
}
