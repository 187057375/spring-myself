package com.ciicgat.springmyself.proxy.cglib;

import com.ciicgat.springmyself.annotation.Async;
import com.ciicgat.springmyself.annotation.advice.Advice;
import com.ciicgat.springmyself.annotation.advice.Aspect;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * Created by sunguangzhu on 2018/8/23.
 */
public class DefaultCallbackFilter implements CallbackFilter {

    public static final Callback[] callbacks = new Callback[]{
            NoOp.INSTANCE,
            AopCallback.INSTANCE,
            AsyncCallback.INSTANCE
    };

    public enum CallbackFilterEnum {
        DEFAULT(0), INTERCEPTOR(1),ASYNC(2);
        private int code;

        CallbackFilterEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }

    private Class<?> clazz;

    public DefaultCallbackFilter(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int accept(Method method) {
        if (method.isAnnotationPresent(Advice.class)){
            return CallbackFilterEnum.INTERCEPTOR.getCode();
        }else if (method.isAnnotationPresent(Async.class)){
            return CallbackFilterEnum.ASYNC.getCode();
        }else {
            return CallbackFilterEnum.DEFAULT.getCode();
        }
    }
}
