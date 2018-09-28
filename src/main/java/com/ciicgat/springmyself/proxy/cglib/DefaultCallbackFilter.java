package com.ciicgat.springmyself.proxy.cglib;

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
            AopCallback.INSTANCE
    };

    public enum CallbackFilterEnum {
        DEFAULT(0), INTERCEPTOR(1);
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
        return CallbackFilterEnum.INTERCEPTOR.getCode();
    }
}
