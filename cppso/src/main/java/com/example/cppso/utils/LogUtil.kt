package com.example.cppso.utils

import android.util.Log

/**
 * @author Henry
 *@Date 2020-02-09  13:14
 *@Email 2427417167@qq.com
 */
class LogUtil{
    companion object{
        var TAG: String = "cppso"
        fun d(msg:String,tag:String=TAG){
            Log.d(tag, msg)
        }

        fun i(msg: String, tag: String = TAG) {
            Log.i(tag, msg)
        }
    }
}