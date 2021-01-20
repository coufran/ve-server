package cn.coufran.ve.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token实体
 * @author coufran
 * @since 1.0.0
 * @version 1.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    /** Token值 */
    @TableId
    private String token;
    /** Token归属用户ID */
    private Integer userId;
    /**
     * Token类型
     * @since 1.1.0
     */
    private Type type;
    /** 过期时间（时间戳） */
    private Long expire;

    /**
     * 构造 ACCESS Token
     * @param token token值
     * @param userId Token归属用户ID
     * @param expire 过期时间
     */
    public Token(String token, Integer userId, long expire) {
        this(token, userId, Type.ACCESS, expire);
    }

    /**
     * Token类型
     * @author coufran
     * @version 1.1.0
     * @since 1.1.0
     */
    public enum Type {
        ACCESS,
        REFRESH
    }
}
