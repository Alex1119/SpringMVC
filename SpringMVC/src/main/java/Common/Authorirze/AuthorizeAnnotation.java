package Common.Authorirze;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorizeAnnotation {
    // 默认验证
    AuthorizeType value() default AuthorizeType.Authority;
}
