package com.ciicgat.springmyself.ioc;

import com.ciicgat.springmyself.processor.ServiceProcessor;

public class IocBeanFactory {

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        if (clazz.isInterface()) {
            beanName = ServiceProcessor.implMap.get(clazz.getSimpleName());
        }
        T proxyObject = (T) IocContainer.beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }
}
