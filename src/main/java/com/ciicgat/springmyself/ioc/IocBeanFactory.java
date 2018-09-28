package com.ciicgat.springmyself.ioc;

public class IocBeanFactory {

    public static <T> T getBean(String beanName) {
        if (beanName.endsWith("Impl")||beanName.endsWith("Aspect")){
        }else {
            beanName = beanName+"Impl";
        }
        T proxyObject = (T) IocContainer.beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        if (beanName.endsWith("Impl")||beanName.endsWith("Aspect")){
        }else {
            beanName = beanName+"Impl";
        }
        T proxyObject = (T) IocContainer.beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        if (beanName.endsWith("Impl")||beanName.endsWith("Aspect")){
        }else {
            beanName = beanName+"Impl";
        }
        T proxyObject = (T) IocContainer.beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }
}
