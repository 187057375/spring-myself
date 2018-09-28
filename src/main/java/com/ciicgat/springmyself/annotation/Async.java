package com.ciicgat.springmyself.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 11:19 2018/9/21
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Async {
    String value() default "";
}
