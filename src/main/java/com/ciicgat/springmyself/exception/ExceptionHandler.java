package com.ciicgat.springmyself.exception;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("-------------------------------------------------");
        System.out.println("=====当前线程为: " + t.getName());
        System.out.println("=====exception is : " + e);
        System.out.println("-------------------------------------------------");
    }
}
