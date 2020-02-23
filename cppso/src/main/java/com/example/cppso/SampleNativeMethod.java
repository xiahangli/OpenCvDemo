package com.example.cppso;

/**
 * Created by glumes on 28/02/2018
 */

public class SampleNativeMethod {

    static {
        System.loadLibrary("native-operation");
    }

    //几个native方法
    public static native String NativeString();

    public static native int[] NativeIntArray();

    public static native int[][] NativeArray();

}
