package cn.coufran.ve.model;

import lombok.Data;

/**
 * 用户
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
public class User {
    /** 用户ID */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
}
