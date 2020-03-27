package com.example.cppso.aviplayer;

import android.graphics.Bitmap;

/**
 * @author Henry
 * @Date 2020-02-23  16:59
 * @Email 2427417167@qq.com
 */
public class AviPlayer {

    static {
        System.loadLibrary("player");
    }


    /**
     *
     * @param filename 路径
     * @return 文件描述符号
     */
    public static native long open(String filename);

    public static native int getHeight(long avi);

    public static native double getFrameRate(long mAvi);


    /**
     *static对应jni的jclass,非static对应jobject
     * @param avi 文件描述符号
     */
    public native static long init(long avi);

    public static native int getWidth(long avi);

    /**
     * 是否读取帧成功
     * @param avi
     * @param bitmap
     * @return
     */
    public native static   boolean  reder(long avi, Bitmap bitmap);
}
