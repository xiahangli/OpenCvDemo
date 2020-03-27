package com.glumes.cppso

import com.example.cppso.SampleNativeMethod
import com.example.cppso.utils.LogUtil


/**
 * Created by glumes on 28/02/2018
 */
class NativeOperation {


    companion object {

        /**
         * 从 Native 中输出 String
         */
        fun getNativeString() {
            //得到native的string
            LogUtil.d("print string from native is " + SampleNativeMethod.NativeString())
        }


        /**
         * JNI 中的数组分为：基本类型数组 和 对象数组
         */
        fun getNativeIntArray(){
//            var intArr =IntArray(10);//int[]
            var intAry = arrayOf(1,2,3,4,5,6,7,8,9,10);

            var array = SampleNativeMethod.NativeIntArray();
            LogUtil.d("size is " + array.size)
            for (it in array){
                LogUtil.d("num $it is $it")
            }
        }

        fun nativeArray() {

        }
    }
}
