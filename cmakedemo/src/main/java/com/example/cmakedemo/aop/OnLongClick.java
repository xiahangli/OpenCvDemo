package com.example.cmakedemo.aop;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @User Xiahangli
 * @Date 2019-11-25  03:28
 * @Email henryatxia@gmail.com
 * @Descrip
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerType = View.OnLongClickListener.class, listenerSetter = "setOnLongClickListener", callbackName = "onLongClick")
public @interface OnLongClick {
    int[] value();
}
