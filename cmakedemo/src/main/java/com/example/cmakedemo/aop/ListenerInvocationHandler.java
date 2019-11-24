package com.example.cmakedemo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

// 将回调的onClick方法拦截，执行我们自己自定义的方法（aop概念）
public class ListenerInvocationHandler implements InvocationHandler {

    // 需要拦截的对象,即activity
    private Object target;
    // 需要拦截的对象键值对
    private HashMap<String, Method> methodHashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }


    /**
     *
     * @param proxy 代理的类，也就是newProxyInstance返回的实例
     * @param method 代理的方法这里可以认为是onClick,因为我们在Proxy.newProxyInstance中将View$OnClickListener作为Class<?>[]{View$OnClickListener}传入
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        Method method1 = methodHashMap.get(name);
//        不能直接return null,因为如果是onLongClickListener则需要返回一个boolean变量
       return method1.invoke(target,args);//调用自己的方法
    }

    /**
     * 将需要拦截的方法添加
     * @param view$OnClickListenerCallbackName 需要拦截的方法，如：onClick()
     * @param method 执行拦截后的方法，如：show(),dddddddddd()等等，这个名字随意，这个方法可以通过反射的方式得到，
     *               为了保证只有标记有EventBase的方法才调用这个方法，需要在反射中判断反射得到的方法（clz.getMethods） 这个方法有
     */
    public void addMtd(String view$OnClickListenerCallbackName, Method method) {
        methodHashMap.put(view$OnClickListenerCallbackName,method);
    }
}