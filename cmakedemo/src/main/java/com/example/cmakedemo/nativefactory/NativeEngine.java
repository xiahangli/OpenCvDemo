package com.example.cmakedemo.nativefactory;

public class NativeEngine {

    static {
        System.loadLibrary("cppso");
    }


    public static native void pringlog();
}