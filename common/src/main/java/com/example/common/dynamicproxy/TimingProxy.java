package com.example.common.dynamicproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @User Xiahangli
 * @Date 2019-11-24  16:19
 * @Email henryatxia@gmail.com
 * @Descrip 耗时统计的处理类
 */
public class TimingProxy {


    private OperationImpl operation;

    public TimingProxy(){

    }

    public void setProxy(OperationImpl op){
        operation = op;
    }

    /**
     * 创建动态代理实例
     *
     * @param clazz
     * @param <T>
     * @return 动态代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.currentTimeMillis();  //方法调用前开始计时
                //这里的this指的是new InvocationHandler实例
                if (operation!=null){
                    Object result = method.invoke(operation, args);
                    Log.i("TimingProxy", method.getName() + " cost time is:" + (System.currentTimeMillis() - start));//方法调用后结束计时，并接下来打印耗时
                    return result;
                }
                return null;
            }
        });
    }

}
