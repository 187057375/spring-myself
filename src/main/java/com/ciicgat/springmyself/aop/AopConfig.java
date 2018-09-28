package com.ciicgat.springmyself.aop;

import lombok.Data;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 10:57 2018/9/27
 */
@Data
public class AopConfig {
    private AopConfig before;
    private AopConfig after;
    private AopConfig afterReturning;
    private AopConfig afterThrowing;
    private AopConfig around;
}
