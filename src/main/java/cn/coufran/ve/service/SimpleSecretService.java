package cn.coufran.ve.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class SimpleSecretService implements SecretService {

    @Override
    public String md5(String data, String salt) {
        return DigestUtils.md5DigestAsHex((data + "." + salt).getBytes());
    }
}
