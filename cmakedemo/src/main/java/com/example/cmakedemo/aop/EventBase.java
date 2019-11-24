package com.example.cmakedemo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @User Xiahangli
 * @Date 2019-11-24  17:30
 * @Email henryatxia@gmail.com
 * @Descrip
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)//放在注解的上面,这里是放在onClick注解上面
public @interface EventBase {
    /**
     * setOnClickListener()
     * @return View.OnclickListener.class 类类型
     */
    Class<?> listenerType();

    /**
     * setOnclickListener的方法名
     * @return 字面量"setOnclickListener"
     */
    String getSetOnClickListenerName();

    /**
     * OnClickListener中的回调方法onClick(View view)
     * @return 字面量 "onClick(View view)"
     */
    String getView$OnClickListenerCallbackName();
}
