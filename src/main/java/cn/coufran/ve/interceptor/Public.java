package cn.coufran.ve.interceptor;

import java.lang.annotation.*;

/**
 * 开放接口标记注解，Public注解可用Controller在方法和类上，存在次方法的请求不要求存在Token。
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 * @see AuthInterceptor
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Public {
}
