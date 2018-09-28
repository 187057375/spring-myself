package com.ciicgat.springmyself.ioc;

import com.ciicgat.springmyself.annotation.Autowired;
import com.ciicgat.springmyself.annotation.Service;
import com.ciicgat.springmyself.processor.AspectProcessor;
import com.ciicgat.springmyself.processor.AutowiredProcessor;
import com.ciicgat.springmyself.processor.ServiceProcessor;
import com.ciicgat.springmyself.proxy.cglib.BeanDefinition;
import com.ciicgat.springmyself.proxy.cglib.DefaultCallbackFilter;
import com.ciicgat.springmyself.util.PackageUtil;
import com.ciicgat.springmyself.xml.ScanXmlBean;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IocContainer {

    public static Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public static ScanXmlBean scanXmlBean = new ScanXmlBean();

    //使用volatile关键字保其可见性
    volatile private static IocContainer instance = null;

    private IocContainer() {
    }

    public static IocContainer getInstance() {
        try {
            if (instance != null) {//懒汉式
            } else {
                synchronized (IocContainer.class) {
                    if (instance == null) {//二次检查
                        instance = new IocContainer();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void init() throws Exception {
        ServiceProcessor.init();
        AutowiredProcessor.init();
        AspectProcessor.init();
    }
}
