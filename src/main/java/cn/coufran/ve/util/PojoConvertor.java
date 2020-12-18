package cn.coufran.ve.util;

public interface PojoConvertor<POJO, BO> {
    POJO from(BO bo);
    POJO to(BO bo);
}
