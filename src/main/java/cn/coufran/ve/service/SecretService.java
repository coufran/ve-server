package cn.coufran.ve.service;

/**
 * 加解密服务，封装了常用的加解密算法，本组件可单例使用，多线程安全
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
public interface SecretService {

    /**
     * MD5加密
     * @param data 数据
     * @param salt 盐
     * @return 加密后字符串（32位小写）
     */
    String md5(String data, String salt);
}
