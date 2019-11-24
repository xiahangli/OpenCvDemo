package com.example.common;

import android.app.Application;
import android.content.res.Configuration;

/**
 * @User Xiahangli
 * @Date 2019-11-24  15:44
 * @Email henryatxia@gmail.com
 * @Descrip 基本上定义了常见的Application方法
 */
public interface ICommonProxy {
    void onCreate(Application application);
    void onTerminate();
    void onLowMemory();
    void onLowMemory(int level);
    void attach();
    void onConfigurationChanged(Configuration configuration);
}
