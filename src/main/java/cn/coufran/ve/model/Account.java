package cn.coufran.ve.model;

import lombok.Data;

/**
 * 会计账户
 * @author coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class Account {
    /** ID */
    private Integer id;
    /** 名称 */
    private String name;
    /** 余额 */
    private Integer amount;
    /** 所属科目（分类） */
    private Integer titleId;
}
