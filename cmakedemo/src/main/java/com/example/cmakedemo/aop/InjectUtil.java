package com.example.cmakedemo.aop;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @User Xiahangli
 * @Date 2019-11-24  17:36
 * @Email henryatxia@gmail.com
 * @Descrip 依赖注入的类
 */
public class InjectUtil {

    // 布局的注入
    public static void injectLayout(Activity activity) {
        // 获取类
        Class<? extends Activity> clazz = activity.getClass();
        // 获取类的注解,注意这个是类的注解，用@Target(ElementType.TYPE)表示
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            // 获取注解的值（R.layout.xxxmain）
            int layoutId = contentView.value();
            try {
                // 获取指定的方法（setContentView）坑：getMethod
                Method method = clazz.getMethod("setContentView", int.class);
                // 执行方法
                method.invoke(activity, layoutId);
                // 另一种写法：
                // activity.setContentView(layoutId);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


    //View字段的注入
    public static void injectView(Activity activity) {
        if (null == activity) return;
        Class<? extends Activity> activityClass = activity.getClass();//得到Activity.class类对象
        Field[] declaredFields = activityClass.getDeclaredFields();//根据类对象得到所有字段，方法
        for (Field field : declaredFields) {
            //解析InjectView 获取button id,注意这个是字段注解，用@Target(ElementType=FIELD)表示


//            field.getAnnotation(InjectView.class)

            InjectView annotation = field.getAnnotation(InjectView.class);//拿到注解
            int viewId = annotation.value();//得到注解的值
            try {
                //方法一：通过反射的方式找到findViewById方法
                Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);//动态的获取findViewById方法
                View view = (View) findViewByIdMethod.invoke(activity, viewId);//调用findViewById得到View
//                    方法二：直接通过activity.findViewById
//                    View viewById = activity.findViewById(viewId);
                field.setAccessible(true);
                field.set(activity, view);//给字段赋值,这时候View字段就被赋值成findViewById的View对象了
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 事件的注入
     *
     * @param activity
     */
    // 事件的注入
    public static void injectEvents(Activity activity) {
        // 获取类
        try {
//            Class<?> clazz = Class.forName("com.example.cmakedemo.MainActivity");//方法一，通过Class.forName得到类对象
            Class<? extends Activity> clazz = activity.getClass();//方法二，通过实例.getClass得到类对象
//              Class<? extends Activity> clazz = MainActivity.class ; //方法三，通过类对象.class得到类类型
            Method[] methods = clazz.getMethods();

            for (int i = 0; i < methods.length; i++) {
                Annotation[] annotations = methods[i].getAnnotations();//方法上的注解组,包含OnClick,等等
                for (int j = 0; j < annotations.length; j++) {
                    Annotation annotation = annotations[j];//方法上的其中一个注解，如OnClick
                    if (annotation!=null){
                        Class<? extends Annotation> aClass = annotation.annotationType();//得到EventBase/Retention类
                        EventBase eventBase = aClass.getAnnotation(EventBase.class);
                        if (eventBase!=null){
                            String setOnClickListenerName = eventBase.getSetOnClickListenerName();
                            Class<?> OnClickListenerClass = eventBase.listenerType();
                            String view$OnClickListenerCallbackName = eventBase.getView$OnClickListenerCallbackName();


                            ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                            Object OnClickListenerproxyInstance = Proxy.newProxyInstance(OnClickListenerClass.getClassLoader(),
                                    new Class<?>[]{OnClickListenerClass},
                                    handler);
                            handler.addMtd(view$OnClickListenerCallbackName,methods[i]);
                            /**另一种方式解析OnClick中的value值*/
                            OnClick onClikmethod = methods[i].getAnnotation(OnClick.class);
                            int[] values = onClikmethod.value();//拿到OnClick的value方法的值
                            for (int k = 0; k < values.length; k++) {
                                Method findViewById = clazz.getMethod("findViewById",int.class);
                                //activity(clazz).findViewById(view)
                                View view =(View) findViewById.invoke(activity, values[k]);
                                //view.setOnclicklistener
                               Method setOnClickLister = view.getClass().getMethod(setOnClickListenerName,OnClickListenerClass);
                                //view.setOnclicklistener(new OnClickListener())
                                setOnClickLister.invoke(view,OnClickListenerproxyInstance);

                            }

                            System.out.println();

                        }
                    }
                }
            }
            // 遍历方法
//            for (Method method : methods) {
//                // 获取每个方法的注解（多个控件id）
//                Annotation[] annotations = method.getAnnotations();
//                // 遍历注解
//                for (Annotation annotation : annotations) {
//
//                    Class<? extends Annotation> annotationType = annotation.annotationType();//返回OnClick类类型
//                    if (annotationType != null) {
//                        EventBase eventBase = annotationType.getAnnotation(EventBase.class);
//                        String setOnClickListenerName = eventBase.getSetOnClickListenerName();
//                        Method[] methods1 = annotationType.getMethods();
//                        for (Method mtd :methods1) {
////                            mtd.invoke(annotation)
//
//                        }
//
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void injectEvents(Activity activity) {
//        if (null == activity) {
//            return;
//        }
////        获取到类类型
//        Class<? extends Activity> activityClass = activity.getClass();
//        //获取该类的所有方法
//        Method[] declaredMethods = activityClass.getDeclaredMethods();
////        遍历所有方法
//        for (Method method : declaredMethods) {
//            Annotation[] annotations = method.getAnnotations();
//            for (Annotation annotation : annotations) {
//                Class<? extends Annotation> annotationType = annotation.annotationType();//得到OnClick这个注解的注解类型
//                if (annotationType != null) {//annotation！=null说明方法上有注解
//                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);//
//                    if(eventBase!=null){//因为我们在OnClick上面添加了EventBase，所以这里不为null
//                        //得到注解上必要的信息
//                        // 事件3大成员
//                        String getSetOnClickListenerName = eventBase.getSetOnClickListenerName();  //setOnClickListener
//
//                        Log.e("--main--","getSetOnClickListenerName:"+getSetOnClickListenerName);
//
//                        Class<?> listenerType = eventBase.listenerType();    //View.OnClickListener.class
//                        String getView$OnClickListenerCallback = eventBase.getView$OnClickListenerCallbackName();  //onClick
//                        Log.e("--main--","getView$OnClickListenerCallback:"+getView$OnClickListenerCallback);
//
//                        // 获取注解的值，执行方法再去获得注解的值
//                        try {
//                            // 通过annotationType获取onClick注解的value值
//                            Method valueMethod = annotationType.getDeclaredMethod("value");
//                            // 执行value方法获得注解的值
//                            int[] viewIds = (int[]) valueMethod.invoke(annotation);
//
//                            // 代理方式（3个成员组合）
//                            // 拦截方法
//                            // 得到监听的代理对象（新建代理单例、类的加载器，指定要代理的对象类的类型、class实例）
//                            ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
//                            // 添加到拦截列表里面
//                            handler.addMtd(getView$OnClickListenerCallback, method);
//                            // 监听对象的代理对象
//                            // ClassLoader loader:指定当前目标对象使用类加载器,获取加载器的方法是固定的
//                            // Class<?>[] interfaces:目标对象实现的接口的类型,使用泛型方式确认类型
//                            // InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法
//                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
//                                    new Class[]{listenerType}, handler);
//
//                            // 遍历注解的值
//                            for (int viewId : viewIds) {
//                                // 获得当前activity的view（赋值）
//                                View view = activity.findViewById(viewId);
//                                // 获取指定的方法
//                                Method setter = view.getClass().getMethod(getSetOnClickListenerName, listenerType); //获取 setOnclickListener 里面的参数 View.Onclick
//                                // 执行方法
//                                setter.invoke(view, listener); //执行setOnclickListener里面的回调 onclick方法
//                            }
//                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println();
//
//                }
//            }
//
//        }
//    }
}
