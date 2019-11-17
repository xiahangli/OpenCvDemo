package com.alibaba.imageprocess;

/**
 * @User Xiahangli
 * @Date 2019-11-17  18:15
 * @Email henryatxia@gmail.com
 * @Descrip
 */
public class GrayUtil {
    static {
        System.loadLibrary("native-lib");
    }

    public native int[] grayImage(int buf[], int w, int h);
}
