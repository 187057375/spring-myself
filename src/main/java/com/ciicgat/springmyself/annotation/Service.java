package com.ciicgat.springmyself.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 11:18 2018/9/21
 */
@Target(ElementType.TYPE) // 类
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
