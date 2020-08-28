package cn.coufran.ve.interceptor;

import cn.coufran.springboot.starter.api.Result;
import cn.coufran.ve.service.AuthService;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("OPTIONS".equals(request.getMethod())) {
            return true;
        }

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
        if(publicAnnotation != null) {
            return true;
        }

        String token = request.getHeader("Token");
        if(token == null) {
            this.writeError(403, "未登录", response);
            return false;
        }
        if(!authService.isLogin(token)) {
            this.writeError(403, "登录已过期，请重新登录", response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("");
    }

    protected void writeError(int code, String msg, HttpServletResponse response) throws IOException {
        this.writeError(Result.error(code, msg), response);
    }

    protected void writeError(Result result, HttpServletResponse response) throws IOException {
        this.writeError(JSON.toJSONString(result), response);
    }

    protected void writeError(String responseStr, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf8");
        response.getWriter().write(responseStr);
    }
}
