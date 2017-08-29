package Common.Authorirze;

import Model.BaseToken;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.TreeMap;

@Service
@Aspect
@Order(2) // 数字越小,优先级越高
public class AuthorizeAspect {

    // 配置切点 及要传的参数
    @Pointcut("@annotation(Common.Authorirze.AuthorizeAnnotation)")
    public void AuthorizePointCut(){}

    // 配置连接点 方法开始执行时通知
    @Before("AuthorizePointCut() && args(request,viewModel)")
    public void beforeLog(HttpServletRequest request, BaseToken viewModel) {
        System.out.println("开始验证签名前置通知");
        Class cls = viewModel.getClass();
        TreeMap<String, Object> params = new TreeMap<>();
        Field[] fields = cls.getDeclaredFields();
        try {
            for(Field f : fields){
                f.setAccessible(true);
                params.put(f.getName(),f.get(viewModel));
                System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(viewModel));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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


}
