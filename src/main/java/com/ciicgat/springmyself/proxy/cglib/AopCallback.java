package com.ciicgat.springmyself.proxy.cglib;

import com.ciicgat.springmyself.annotation.advice.*;
import com.ciicgat.springmyself.ioc.IocContainer;
import com.ciicgat.springmyself.processor.AspectProcessor;
import com.ciicgat.springmyself.xml.ScanXmlBean;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 16:40 2018/9/26
 */
public class AopCallback implements MethodInterceptor {

    public static final AopCallback INSTANCE = new AopCallback();

    private AopCallback() {
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //判断当前对象是否被拦截
        ScanXmlBean scanXmlBean = IocContainer.scanXmlBean;
        String name = obj.getClass().getPackage().getName();
        if (name.startsWith(scanXmlBean.getInterceptorPackage()) && method.isAnnotationPresent(Advice.class)) {
            //拦截器处理
            return interceptHandle(obj, method, args, methodProxy);
        }
        return methodProxy.invokeSuper(obj, args);
    }


    private Object interceptHandle(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object o = null;
        Map<Method, String> aspectMap = AspectProcessor.aspectMap;
        if (aspectMap.size() == 0) {
            return methodProxy.invokeSuper(obj, args);
        }
        String arg = aspectMap.get(method);
        if (arg.equals("") || arg == null) {
            return methodProxy.invokeSuper(obj, args);
        }
        BeanDefinition beanDefinition = IocContainer.beanDefinitionMap.get(arg);
        Object proxyObject = beanDefinition.getObject();
        Method[] declaredMethods = proxyObject.getClass().getMethods();
        Map<String, Method> methodMap = new HashMap<>();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Before.class)) {//前置通知
                methodMap.put("Before", declaredMethod);
            } else if (declaredMethod.isAnnotationPresent(AfterThrowing.class)) {//环绕通知
                methodMap.put("AfterThrowing", declaredMethod);
            } else if (declaredMethod.isAnnotationPresent(Around.class)) {//环绕通知
                methodMap.put("Around", declaredMethod);
            } else if (declaredMethod.isAnnotationPresent(AfterReturning.class)) {//返回后通知
                methodMap.put("AfterReturning", declaredMethod);
            } else if (declaredMethod.isAnnotationPresent(After.class)) {//返回后通知
                methodMap.put("After", declaredMethod);
            }
        }

        //前置通知
        if (methodMap.get("Before") != null) {
            methodMap.get("Before").invoke(proxyObject);
        }
        //环绕通知
        if (methodMap.get("Around") != null) {
            methodMap.get("Around").invoke(proxyObject);
        }
        //目标方法
        try {
            o = methodProxy.invokeSuper(obj, args);
        } catch (Exception e) {
            //被拦截方法
            if (methodMap.get("AfterThrowing") != null) {
                methodMap.get("AfterThrowing").invoke(proxyObject);
            } else {
                e.printStackTrace();
            }
        }
        //环绕通知
        if (methodMap.get("Around") != null) {
            methodMap.get("Around").invoke(proxyObject);
        }
        //返回后通知
        if (methodMap.get("AfterReturning") != null) {
            methodMap.get("AfterReturning").invoke(proxyObject);
        }
        return o;
    }
}
