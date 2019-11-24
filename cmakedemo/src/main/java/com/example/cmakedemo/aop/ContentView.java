package com.example.cmakedemo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @User Xiahangli
 * @Date 2019-11-24  18:01
 * @Email henryatxia@gmail.com
 * @Descrip 注入布局
 */
@Target(ElementType.TYPE) // 该注解作用于类，接口或者枚举类型上
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，jvm加载时可以通过反射获取到该注解的内容
public @interface ContentView {
    int value();
}
