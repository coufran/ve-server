package cn.coufran.ve.util;

import cn.coufran.ve.model.Token;
import cn.coufran.ve.model.User;

import java.util.UUID;

public class TokenGenerator {
    /**
     * 生成Token（随机UUID）
     * @param type Token类型
     * @param user Token所属用户
     * @param activeMinutes Token有效时间，以分钟计算
     * @return 生成的Token
     */
    public static Token generateToken(Token.Type type, User user, int activeMinutes) {
        return TokenGenerator.generateToken(type, user.getId(), activeMinutes);
    }

    /**
     * 生成Token（随机UUID）
     * @param type Token类型
     * @param userId Token所属用户ID
     * @param activeMinutes Token有效时间，以分钟计算
     * @return 生成的Token
     */
    public static Token generateToken(Token.Type type, Integer userId, int activeMinutes) {
        String tokenString = UUID.randomUUID().toString().replaceAll("-", "");
        long expire = System.currentTimeMillis() + 1000 * 60 * activeMinutes; // 有效期30min
        Token token = new Token(tokenString, userId, type, expire);
        return token;
    }
}
