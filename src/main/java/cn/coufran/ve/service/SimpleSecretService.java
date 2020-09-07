package cn.coufran.ve.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 加解密组件简单实现
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class SimpleSecretService implements SecretService {

    @Override
    public String md5(String data, String salt) {
        return DigestUtils.md5DigestAsHex((data + "." + salt).getBytes());
    }
}
