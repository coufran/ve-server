package cn.coufran.ve.api.vo;

import cn.coufran.ve.model.Title;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class RecordVo {
    private Integer id;
    private Integer amount;
    private AccountVo debit;
    private AccountVo credit;
    private Integer groupId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private String remark;

    public boolean isIncome() {
        return this.credit.getTitle().getKind() == Title.Kind.PROFIT;
    }

    public boolean isExpend() {
        return this.debit.getTitle().getKind() == Title.Kind.LOSS;
    }
}
