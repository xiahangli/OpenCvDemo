package com.example.cmakedemo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @User Xiahangli
 * @Date 2019-11-24  17:32
 * @Email henryatxia@gmail.com
 * @Descrip 注入view,减少findViewById的使用
 */
@Target(ElementType.FIELD)//该注解只作用于字段上
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    int value();//使用value的名字，则在使用注解的时候不需要(方法名(即这里的value)= R.id.xxx)的形式
}
