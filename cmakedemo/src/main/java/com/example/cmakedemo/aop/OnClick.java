package com.example.cmakedemo.aop;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @User Xiahangli
 * @Date 2019-11-24  17:26
 * @Email henryatxia@gmail.com
 * @Descrip 简化事件setOnClickListener事件的注解
 */

@Target(ElementType.METHOD)//作用对象是方法
@Retention(RetentionPolicy.RUNTIME)//一直保留到运行时，运行时也存在
//下面的的EventBase是注解的注解，即放在注解上的
//注意第三个参数必须是onClick,即setOnClickListener（new OnClickListener）中的OnClickListener的回调函数
@EventBase(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", callbackName = "onClick")
public @interface OnClick {
    int[] value();//使用val的名字，在使用注解的时候需要val={xxx1,xxx2,...}的方式
}
