package com.ciicgat.springmyself.annotation.Aspect;

import com.ciicgat.springmyself.annotation.advice.*;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 14:56 2018/9/27
 */
@Aspect
public class PersonAspect {

    @Before
    public void before(){
        System.out.println("-----------------------------PersonAspect before-----------------------------");
    }
    @After
    public void after(){
        System.out.println("-----------------------------PersonAspect after-----------------------------");
    }
    @AfterReturning
    public void afterReturning(){
        System.out.println("-----------------------------PersonAspect afterReturning-----------------------------");
    }
    @AfterThrowing
    public void afterThrowing(){
        System.out.println("-----------------------------PersonAspect afterThrowing-----------------------------");
    }
    @Around
    public Object around(Object obj,Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----------------------------PersonAspect around start-----------------------------");
        Object o = methodProxy.invokeSuper(obj, args);
        System.out.println("-----------------------------PersonAspect around end-----------------------------");
        return o;
    }
}
