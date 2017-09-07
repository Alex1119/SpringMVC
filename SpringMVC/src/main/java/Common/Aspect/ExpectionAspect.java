package Common.Aspect;

import Model.Response;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Service@Scope("request")
@Order(1) // 数字越小,优先级越高
public class ExpectionAspect {

    // 配置切点 及要传的参数
    @Pointcut("execution(* Controller.*.*(..))")
    public void pointCut(){}

    // 配置连接点 方法开始执行时通知
    @Before("pointCut()")
    public void beforeLog() {
        System.out.println("开始执行前置通知");
    }

    //    方法执行完后通知
    @After("pointCut()")
    public void afterLog() {
        System.out.println("开始执行后置通知");
    }

    //    执行成功后通知
    @AfterReturning("pointCut()")
    public void afterReturningLog() {
        System.out.println("方法成功执行后通知");
    }

    //    抛出异常后通知
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingLog(Exception ex) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        InternalErrorHandler(response, ex);
    }

//    //    环绕通知
//    @Around("pointCut()")
//    public Object aroundLog(ProceedingJoinPoint joinpoint) {
//        Object result = null;
//        try {
//            System.out.println("环绕通知开始 日志记录");
//            long start = System.currentTimeMillis();
//
//            //有返回参数 则需返回值
//            result =  joinpoint.proceed();
//
//            long end = System.currentTimeMillis();
//            System.out.println("总共执行时长" + (end - start) + " 毫秒");
//            System.out.println("环绕通知结束 日志记录");
//        } catch (Throwable t) {
//            System.out.println("出现错误");
//            return new Response(500, t.getMessage(), null);
//        }
//        return result;
//    }

    private void InternalErrorHandler(HttpServletResponse response, Exception ex){
        // TODO: 记录错误日志
        Response responseData = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),null);
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
