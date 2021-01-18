package cn.coufran.ve.interceptor;

import cn.coufran.springboot.starter.api.Result;
import cn.coufran.ve.service.AuthService;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 权限校验拦截器，基于Token进行拦截校验。
 * 如果请求头中未包含Token，或包含Token但Token作物或已过期，则拦截并返回标准result，result code是403（非HTTP响应403）。
 * 可在Controller类或方法上使用{@link Public}注解指定不需要Token校验的请求。
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 * @see Public
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    /** 权限控制组件 */
    @Resource
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域预检请求“OPTIONS”不应被拦截
        if("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 检查方法或类上的Public注解
        Public publicAnnotation = null;
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            publicAnnotation = method.getAnnotation(Public.class);
            if(publicAnnotation == null) {
                Class<?> handlerClass = method.getDeclaringClass();
                publicAnnotation = handlerClass.getAnnotation(Public.class);
            }
        }
        // 如果存在Public注解，放行
        if(publicAnnotation != null) {
            return true;
        }

        // 检查请求头
        String token = request.getHeader("Token");
        // 不包含请求头，拦截
        if(token == null) {
            this.writeError(401, "未登录", response);
            return false;
        }
        // 请求头错误或过期，拦截
        if(!authService.isLogin(token)) {
            this.writeError(401, "登录已过期，请重新登录", response);
            return false;
        }

        // 其他情况，放行
        return true;
    }

    /**
     * 向response写入响应数据，响应状态码和响应消息会被包装成标准的Result对象，然后通过JSON序列化写入response
     * @param code 响应状态码
     * @param msg 响应消息
     * @param response 响应
     * @throws IOException response写错误
     */
    protected void writeError(int code, String msg, HttpServletResponse response) throws IOException {
        this.writeError(Result.error(code, msg), response);
    }

    /**
     * 向response写入响应数据
     * @param result 响应数据，result对象会被序列化成JSON串
     * @param response 响应
     * @throws IOException response写错误
     */
    protected void writeError(Result result, HttpServletResponse response) throws IOException {
        this.writeError(JSON.toJSONString(result), response);
    }

    /**
     * 向response写入响应数据，默认ContentType是application/json
     * @param responseStr 响应数据字符串
     * @param response 响应
     * @throws IOException response写错误
     */
    protected void writeError(String responseStr, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf8");
        response.getWriter().write(responseStr);
    }
}
