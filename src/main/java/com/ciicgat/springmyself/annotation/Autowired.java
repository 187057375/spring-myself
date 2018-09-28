package com.ciicgat.springmyself.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 11:19 2018/9/21
 */
@Target(ElementType.FIELD) //描述方法的
@Retention(RetentionPolicy.RUNTIME) // 仅运行时保留
public @interface Autowired {
}
