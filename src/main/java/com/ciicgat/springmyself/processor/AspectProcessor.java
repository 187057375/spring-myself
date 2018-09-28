package com.ciicgat.springmyself.processor;

import com.ciicgat.springmyself.annotation.advice.Advice;
import com.ciicgat.springmyself.annotation.advice.Aspect;
import com.ciicgat.springmyself.ioc.IocContainer;
import com.ciicgat.springmyself.proxy.cglib.BeanDefinition;
import com.ciicgat.springmyself.util.PackageUtil;
import com.ciicgat.springmyself.xml.ScanXmlBean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 14:23 2018/9/27
 */
public class AspectProcessor {

    public static Map<Method, String> aspectMap = new HashMap<>();


    public static void aspectInit(ScanXmlBean scanXmlBean) throws Exception {
        String[] packageScans = scanXmlBean.getPackageScan().split(",");
        for (String packageScan : packageScans) {
            List<Class<?>> classList = PackageUtil.getClass(packageScan, true);
            for (Class<?> aClass : classList) {
                if (aClass.isAnnotationPresent(Aspect.class)) {
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setObject(aClass.newInstance());
//                    Enhancer enhancer = new Enhancer();
//                    Callback[] callbacks = DefaultCallbackFilter.callbacks;
//                    enhancer.setCallbacks(callbacks);
//                    enhancer.setCallbackFilter(new DefaultCallbackFilter(aClass));
//                    Object object = enhancer.create();
//                    beanDefinition.setProxyObject(object);
                    IocContainer.beanDefinitionMap.put(aClass.getSimpleName(),beanDefinition );
                }
            }
        }
    }

    public static void init() throws Exception {
        ScanXmlBean scanXmlBean = IocContainer.scanXmlBean;
        aspectInit(scanXmlBean);
        String[] packageScans = scanXmlBean.getInterceptorPackage().split(",");
        for (String packageScan : packageScans) {
            List<Class<?>> classList = PackageUtil.getClass(packageScan, true);
            for (Class<?> aClass : classList) {
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(Advice.class)) {
                        Advice annotation = declaredMethod.getAnnotation(Advice.class);
                        aspectMap.put(declaredMethod, annotation.value());
                    }
                }
            }
        }
    }
}
