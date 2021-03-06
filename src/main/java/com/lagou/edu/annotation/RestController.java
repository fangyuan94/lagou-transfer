package com.lagou.edu.annotation;

import java.lang.annotation.*;

/**
 * @author fangyuan
 * service注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestController {

    //引用id 若为空
    String value() default "";

    String url();
}
