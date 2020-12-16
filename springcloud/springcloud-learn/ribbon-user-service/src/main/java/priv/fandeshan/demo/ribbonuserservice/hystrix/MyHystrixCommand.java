package priv.fandeshan.demo.ribbonuserservice.hystrix;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyHystrixCommand {

    int timeout() default 1000;

    String fallback() default "";
}
