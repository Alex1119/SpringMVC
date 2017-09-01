package Common.Authorirze;

import Model.Response;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 权限认证拦截器
 *
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            Class<?> clazz = hm.getBeanType();
            Method m = hm.getMethod();
            try {
                if (clazz != null && m != null) {
                    boolean isClzAnnotation = clazz.isAnnotationPresent(AuthorizeAnnotation.class);
                    boolean isMethondAnnotation = m.isAnnotationPresent(AuthorizeAnnotation.class);
                    AuthorizeAnnotation authority = null;
                    // 如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
                    if (isMethondAnnotation) {
                        authority = m.getAnnotation(AuthorizeAnnotation.class);
                    } else if (isClzAnnotation) {
                        authority = clazz.getAnnotation(AuthorizeAnnotation.class);
                    }
                    if (authority != null) {
                        if (AuthorizeType.NoAuthority == authority.value()) {
                            // 不验证权限
                            return true;
                        } else {
                            // 验证权限
                            // TODO:
                            if (true){
                                return true;
                            }
                            UnAuthorizeHandler(response);
                        }
                    }
                    return false;
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }

    // 未通过验证，返回提示json
    private void UnAuthorizeHandler(HttpServletResponse response) {
        Response responseData = new Response(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),null);
        String json = new GsonBuilder()
                .serializeNulls().create()
                .toJson(responseData);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}