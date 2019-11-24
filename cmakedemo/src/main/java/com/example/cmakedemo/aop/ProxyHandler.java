package com.example.cmakedemo.aop;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @User Xiahangli
 * @Date 2019-11-24  17:44
 * @Email henryatxia@gmail.com
 * @Descrip
 */
public class ProxyHandler  implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("ProxyHandler", "method name = " + method.getName() + " and args = " + Arrays.toString(args));

//        Object handler = mHandlerRef.get();

//        if (null == handler) return null;

        String name = method.getName();

        //将onClick方法的调用映射到activity 中的invokeClick()方法
//        Method realMethod = mMethodHashMap.get(name);
//        if (null != realMethod){
//            return realMethod.invoke(handler, args);
//        }

        return null;
    }
}
