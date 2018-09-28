package com.ciicgat.springmyself.pool;

import com.ciicgat.springmyself.exception.ExceptionHandler;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 */
public class MyThreadPoolExecutor {

    {
        init();
    }

    public static ThreadPoolExecutor pool = null;

    //核⼼线程数最⼤值
    public static int COREPOOL_SIZE = 10;
    //线程总数最⼤值
    public static int MAXIMUMPOOL_SIZE = 30;
    //⾮核⼼线程闲置超时时⻓
    public static int KEEP_ALIVE_TIME = 30;
    //该线程池中的任务队列：维护着等待执⾏的Runnable对象
    public static int CAPACITY = 10;

    public void init() {
        pool = new ThreadPoolExecutor(
                COREPOOL_SIZE,
                MAXIMUMPOOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue(CAPACITY),
                new MyThreadFactory(),
                new MyRejectedExecutionHandler());
    }

    public static void destory() {
        if (pool != null) {
            System.out.println("--------------线程池被销毁---------------");
            pool.shutdownNow();
        }
    }

    public ExecutorService getMyThreadPoolExecutor() {
        System.out.println("--------------线程池被调用---------------");
        return this.pool;
    }

    /**
     * 自定义线程工厂
     */
    public static class MyThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = MyThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
//            System.out.println("线程池当前线程名称为: = " + threadName);
            t.setName(threadName);
            t.setUncaughtExceptionHandler(new ExceptionHandler());
            return t;
        }
    }

    /**
     * 自定义策略:
     */
    private class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("--------------自定义策略被执行-------------");
        }


    }
}