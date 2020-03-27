package com.example.cmakedemo

import android.app.Application
import com.example.common.CommonApplication
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

/**
 * @author Henry
 *@Date 2020-02-09  12:16
 *@Email 2427417167@qq.com
 */
//application有构造函数，必须加（）
class SoApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        val clz = CommonApplication::class.java
        val enums = clz.enumConstants
        (enums?.get(1) as CommonApplication).onCreate(this)
        initLeakCanary()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

}