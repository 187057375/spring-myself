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
public class StudentAspect {

    @Before
    public void before(){
        System.out.println("-----------------------------StudentAspect before-----------------------------");
    }
    @After
    public void after(){
        System.out.println("-----------------------------StudentAspect after-----------------------------");
    }
    @AfterReturning
    public void afterReturning(){
        System.out.println("-----------------------------StudentAspect afterReturning-----------------------------");
    }
    @AfterThrowing
    public void afterThrowing(){
        System.out.println("-----------------------------StudentAspect afterThrowing-----------------------------");
    }

    @Around
    public Object around(Object obj,Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----------------------------StudentAspect around start-----------------------------");
        Object o = methodProxy.invokeSuper(obj, args);
        System.out.println("-----------------------------StudentAspect around end-----------------------------");
        return o;
    }
}
