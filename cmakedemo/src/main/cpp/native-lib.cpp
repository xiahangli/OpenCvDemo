//
// Created by xiahangli on 2019/2/2.
//

#include <jni.h>
//#include "native-lib.h"
//#include "opencv2/opencv.hpp"
#include <jni.h>
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;

/**
 * 必须添加extern "C"，否则报
 * java.lang.UnsatisfiedLinkError:
 * No implementation found for java.lang.String com.example.cmakedemo.MainActivity.getC()
 * (tried Java_com_example_cmakedemo_MainActivity_getC
 * and Java_com_example_cmakedemo_MainActivity_getC__)
 */
extern "C"

JNIEXPORT jstring JNICALL
Java_com_example_cmakedemo_MainActivity_getC(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(
            "Hello from JNI !  Compiled with ABI "  ".");//注意这里是c++中的写法,c中的写法需要带*,见jni.h中的定义
}


JNIEXPORT jintArray JNICALL Java_com_example_cmakedemo_MainActivity_gray(
        JNIEnv *env, jclass obj, jintArray buf, int w, int h) {

    jint *cbuf;
    cbuf = (env)->GetIntArrayElements(buf, JNI_FALSE);
    if (cbuf == NULL) {
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char *) cbuf);

    uchar *ptr = imgData.ptr(0);
    for (int i = 0; i < w * h; i++) {
        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
        //对于一个int四字节，其彩色值存储方式为：BGRA
        int grayScale = (int) (ptr[4 * i + 2] * 0.299 + ptr[4 * i + 1] * 0.587 +
                               ptr[4 * i + 0] * 0.114);
        ptr[4 * i + 1] = grayScale;
        ptr[4 * i + 2] = grayScale;
        ptr[4 * i + 0] = grayScale;
    }

    int size = w * h;
    jintArray result = (env)->NewIntArray(size);
    (env)->SetIntArrayRegion(result, 0, size, cbuf);
    (env)->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_cmakedemo_MainActivity_getGray(JNIEnv *env, jobject instance, jintArray buf_,
                                                jint w, jint h) {
    jint *buf = env->GetIntArrayElements(buf_, NULL);

    if (buf == NULL)return 0;
    Mat imgData(h, w, CV_8UC4, (unsigned char *) buf);//创建Mat矩阵对象

    uchar *ptr = imgData.ptr(0);
    for (int i = 0; i < w * h; i++) {
        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
        //对于一个int四字节，其彩色值存储方式为：BGRA,注意这是opencv对像素的存储
        int grayScale = (int) (ptr[4 * i + 2] * 0.299 + ptr[4 * i + 1] * 0.587 +
                               ptr[4 * i + 0] * 0.114);
        ptr[4 * i + 1] = grayScale;
        ptr[4 * i + 2] = grayScale;
        ptr[4 * i + 0] = grayScale;
    }
    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, buf);
    env->ReleaseIntArrayElements(buf_, buf, 0);
    return result;
}
