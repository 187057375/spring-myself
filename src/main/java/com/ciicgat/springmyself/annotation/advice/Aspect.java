package com.ciicgat.springmyself.annotation.advice;

import java.lang.annotation.*;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 14:19 2018/9/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Aspect {
    String value() default "";
}
