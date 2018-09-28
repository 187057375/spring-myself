package com.ciicgat.springmyself.annotation.Aspect;

import com.ciicgat.springmyself.annotation.advice.*;

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
    public void around(){
        System.out.println("-----------------------------PersonAspect around-----------------------------");
    }
}
