package com.lagou.edu.annotation;

import java.lang.annotation.*;

/**
 *
 * @author fangyuan
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    //引用id 若为空
    String value() ;
}
