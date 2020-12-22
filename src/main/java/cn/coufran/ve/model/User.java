package cn.coufran.ve.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
public class User implements Serializable {
    /** 用户ID */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    @JSONField(serialize = false)
    private String password;
}
