package cn.coufran.ve.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @TableId
    private String token;
    /** 用户ID */
    private Integer userId;
    /** 过期时间（时间戳） */
    private long expire;
}
