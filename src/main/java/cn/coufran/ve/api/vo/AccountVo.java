package cn.coufran.ve.api.vo;

import cn.coufran.ve.model.Title;
import lombok.Data;

@Data
public class AccountVo {
    private Integer id;
    private String name;
    private Integer amount;
    private Title title;
}
