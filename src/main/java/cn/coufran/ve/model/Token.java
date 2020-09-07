package cn.coufran.ve.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token实体
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    /** Token值 */
    @TableId
    private String token;
    /** Token归属用户ID */
    /** 用户ID */
    private Integer userId;
    /** 过期时间（时间戳） */
    private long expire;
}
