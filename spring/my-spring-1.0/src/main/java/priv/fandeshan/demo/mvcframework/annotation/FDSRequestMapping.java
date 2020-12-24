package priv.fandeshan.demo.mvcframework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FDSRequestMapping {
    String value() default "";
}
