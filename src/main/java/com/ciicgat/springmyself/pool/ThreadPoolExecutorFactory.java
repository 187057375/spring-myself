package com.ciicgat.springmyself.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 15:20 2018/8/21
 */
public class ThreadPoolExecutorFactory {

    public static Executor taskExecutor() {
        ThreadPoolExecutor pool = MyThreadPoolExecutor.pool;
        if (MyThreadPoolExecutor.pool == null) {
            MyThreadPoolExecutor executor = new MyThreadPoolExecutor();
            ExecutorService poolExecutor = executor.getMyThreadPoolExecutor();
            return poolExecutor;
        } else {
            return MyThreadPoolExecutor.pool;
        }
    }
}
