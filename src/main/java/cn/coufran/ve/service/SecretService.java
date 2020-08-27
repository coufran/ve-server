package cn.coufran.ve.service;

public interface SecretService {

    String md5(String data, String salt);
}
