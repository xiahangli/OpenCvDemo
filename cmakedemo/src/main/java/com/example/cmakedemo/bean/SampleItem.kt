package com.example.cmakedemo.bean

import android.util.Log
import android.view.View
import com.example.cmakedemo.nativefactory.NativeEngine

/**
 * @author Henry
 *@Date 2020-02-09  10:06
 *@Email 2427417167@qq.com
 */
class SampleItem (var title:String,var func: View.OnClickListener){}

class NativeCrashSample: View.OnClickListener{
    override fun onClick(v: View?) {
        Log.d("SampleItem", "OnClick")
        NativeEngine.pringlog()
    }

}