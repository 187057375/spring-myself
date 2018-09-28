package com.ciicgat.springmyself.proxy.cglib;

import com.ciicgat.springmyself.annotation.Async;
import com.ciicgat.springmyself.ioc.IocContainer;
import com.ciicgat.springmyself.pool.ThreadPoolExecutorFactory;
import com.ciicgat.springmyself.xml.ScanXmlBean;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 16:43 2018/9/28
 */
public class AsyncCallback implements MethodInterceptor {

    public static final AsyncCallback INSTANCE = new AsyncCallback();

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        ScanXmlBean scanXmlBean = IocContainer.scanXmlBean;
        String name = object.getClass().getPackage().getName();
        if (name.startsWith(scanXmlBean.getInterceptorPackage()) && method.isAnnotationPresent(Async.class)) {
            return async(object,args,methodProxy);
        }else {
            return methodProxy.invokeSuper(object, args);
        }
    }

    private Object async(Object object, Object[] args, MethodProxy methodProxy) {
        Executor executor = ThreadPoolExecutorFactory.taskExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Object o = methodProxy.invokeSuper(object, args);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        return null;
    }
}
