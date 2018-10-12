package com.ciicgat.springmyself.processor;

import com.alibaba.fastjson.JSONObject;
import com.ciicgat.springmyself.annotation.Service;
import com.ciicgat.springmyself.ioc.ClassPathXmlApplicationContext;
import com.ciicgat.springmyself.ioc.IocContainer;
import com.ciicgat.springmyself.proxy.cglib.BeanDefinition;
import com.ciicgat.springmyself.proxy.cglib.DefaultCallbackFilter;
import com.ciicgat.springmyself.util.PackageUtil;
import com.ciicgat.springmyself.xml.ScanXmlBean;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 14:48 2018/9/25
 */
public class ServiceProcessor {

    public static Map<String, String> implMap = new HashMap<>();

    private static void createBeanDefinitionMap(String packageName, File file) throws Exception {
        if (file.isFile()) {
            String fileName = file.getName();
            //判断类型<目前只遍历类>
            if (fileName.substring(fileName.lastIndexOf("."), fileName.length()).equals(".class")) {
                if (packageName != "") {
                    packageName += ".";
                }
                String className = packageName + fileName.replace(".class", "");
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Service.class)) {
                    initBeanDefinition(clazz);
                }
            }
        } else {
            File[] files = file.listFiles();
            if (packageName != "") {
                packageName += ".";
            }
            packageName += file.getName();
            for (File f : files) {
                createBeanDefinitionMap(packageName, f);
            }
        }
    }

    private static void initBeanDefinition(Class<?> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            implMap.put(interfaces[0].getSimpleName(), clazz.getSimpleName());
        }
        Map<String, BeanDefinition> beanDefinitionMap = IocContainer.beanDefinitionMap;
        final Service annotation = clazz.getAnnotation(Service.class);
        String key = null;
        if (annotation.value() == null || "".equals(annotation.value())) {
            key = clazz.getSimpleName();
        } else {
            key = annotation.value();
        }
        BeanDefinition beanDefinition = new BeanDefinition();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        Callback[] callbacks = DefaultCallbackFilter.callbacks;
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new DefaultCallbackFilter(clazz));
        Object object = enhancer.create();
        beanDefinition.setProxyObject(object);
        beanDefinitionMap.put(key, beanDefinition);
    }

    public static void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        IocContainer.scanXmlBean = JSONObject.parseObject(JSONObject.toJSONString(applicationContext.getBean("scanXmlBean")), ScanXmlBean.class);
        String[] packageScans = IocContainer.scanXmlBean.getPackageScan().split(",");
        for (String packageScan : packageScans) {
            List<Class<?>> classList = PackageUtil.getClass(packageScan, true);
            for (Class<?> aClass : classList) {
                if (aClass.isAnnotationPresent(Service.class)) {
                    initBeanDefinition(aClass);
                }
            }
        }
    }
}
