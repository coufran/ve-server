package cn.coufran.ve.api.vo;

import cn.coufran.ve.model.Token;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端Token
 * @author coufran
 * @version 1.1.0
 * @since 1.1.0
 */
@Data
public class ClientTokenVo implements Serializable {
    /** access token */
    private String accessToken;
    /** access token过期时间 */
    private Long accessExpire;
    /** refresh token */
    private String refreshToken;
    /** refresh token过期时间 */
    private Long refreshExpire;

    public ClientTokenVo(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken.getToken();
        this.accessExpire = accessToken.getExpire();
        this.refreshToken = refreshToken.getToken();
        this.refreshExpire = refreshToken.getExpire();
    }
}
