package com.ciicgat.springmyself.processor;

import com.ciicgat.springmyself.annotation.Autowired;
import com.ciicgat.springmyself.ioc.IocContainer;
import com.ciicgat.springmyself.proxy.cglib.BeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 14:51 2018/9/25
 */
public class AutowiredProcessor {

    public static void autowired() throws Exception {
        Map<String, BeanDefinition> beanDefinitionMap = IocContainer.beanDefinitionMap;
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Object proxyObject = entry.getValue().getProxyObject();
            Class<?> superclass = proxyObject.getClass().getSuperclass();
            Field[] declaredFields = superclass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    String simpleName = declaredField.getType().getSimpleName()+"Impl";
                    Object o2 = beanDefinitionMap.get(simpleName).getProxyObject();
                    if (o2 == null) {
                        throw new Exception("can not find bean " + simpleName);
                    }
                    declaredField.setAccessible(true);
                    declaredField.set(proxyObject, o2);
                }
            }
        }
    }
}
