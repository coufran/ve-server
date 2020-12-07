package cn.coufran.ve.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 收支记录
 * @author coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class Record {
    /** ID */
    private Integer id;
    /** 金额 */
    private Integer amount;
    /** 借方 */
    private Integer debitId;
    /** 贷方 */
    private Integer creditId;
    /** 联合ID（用于关联其他记录） */
    private Integer groupId;
    /** 时间 */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    /** 备注 */
    private String remark;
}

