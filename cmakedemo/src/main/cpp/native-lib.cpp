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
#include <android/log.h>
#include <lib_log/LogUtil.h>

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

extern "C"
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
    jint x = 0, y;
    if (buf == NULL)return 0;
    Mat imgData(h, w, CV_8UC4, (unsigned char *) buf);//创建Mat矩阵对象

//    Mat& src = new Mat();
    ////////
    Mat gray_src, dst;
    int height = imgData.rows;
    int width = imgData.cols;
//////    const Mat& 直接传imgData
//////第一个参数为输入src,第二个参数为输出dst
    cvtColor(imgData, gray_src, COLOR_RGB2RGBA, 0);//
    int nc = imgData.channels();
    unsigned char  *ptr = imgData.ptr(0);
    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            if (nc == 1) {
                int gray = gray_src.at<uchar>(row, col);
                buf[row * width + col] = gray;//255 -
            } else if (nc == 4) {//
                int b = imgData.at<Vec4b>(row, col)[0];
                int g = imgData.at<Vec4b>(row, col)[1];
                int r = imgData.at<Vec4b>(row, col)[2];
//                int alpha = imgData.at<Vec4b>(row, col)[3];
                /*dst.at<Vec3b>(row, col)[0] = b;
                dst.at<Vec3b>(row, col)[1] = g;
                dst.at<Vec3b>(row, col)[2] = 0;*/
//保存安卓端需要的像素数据
                ptr[row * width * 4 + col * 4] =
                        (r * 76 + g * 150 + b * 30) >> 8;//max(r, max(g, b))
                ptr[row * width * 4 + col * 4 + 1] =
                        (r * 76 + g * 150 + b * 30) >> 8;//max(r, max(g, b))
                ptr[row * width * 4 + col * 4 + 2] =
                        (r * 76 + g * 150 + b * 30) >> 8;//max(r, max(g, b))
                ptr[row * width * 4 + col * 4 + 3] = 255;
//                buf[row*width*4+col] = (r * 76 + g * 150 + b * 30) >> 8;//max(r, max(g, b))
            }
        }
    }
    jintArray result = env->NewIntArray(height * width);
//    //将buf中的值复制到jintArray中去，数组copy
    env->SetIntArrayRegion(result, 0, height * width, buf);

    env->ReleaseIntArrayElements(buf_, buf, 0);
    return result;


//    uchar *ptr = imgData.ptr(0);
//    for (int i = 0; i < w * h; i++) {
//        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
//        //对于一个int四字节，其彩色值存储方式为：BGRA,注意这是opencv对像素的存储
//        int grayScale = (int) (ptr[4 * i + 2] * 0.299 + ptr[4 * i + 1] * 0.587 +
//                               ptr[4 * i + 0] * 0.114);
//        if(grayScale!=0){
//            int i = 1;
//        }
//        grayScale =(ptr[4 * i + 0] * 76 + ptr[4 * i + 1] * 150 + ptr[4 * i + 2] * 30) >> 8;
//        ptr[4 * i + 1] = grayScale;
//        ptr[4 * i + 2] = grayScale;
//        ptr[4 * i + 0] = grayScale;
//        grayScale = 1;
//    }
//    int size = w * h;
//  // 创建一个int型的数组，大小为图片的大小
//    jintArray result = env->NewIntArray(size);
////    将buf中的值复制到jintArray中去，数组copy
//    env->SetIntArrayRegion(result, 0, size, buf);
//
//    env->ReleaseIntArrayElements(buf_, buf, 0);
//    return result;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_cmakedemo_MainActivity_roi(JNIEnv *env, jobject obj1, jintArray buf_, jint w,
                                            jint h) {

    jint *cbuf;
    cbuf = env->GetIntArrayElements(buf_, JNI_FALSE);
    if (cbuf == NULL) {
        return 0;
    }

    Mat srcImage(h, w, CV_8UC4, (unsigned char *) cbuf);//构造函数

    //拷贝需要复制的图像区域
    Mat partImage;
    srcImage(Rect(0, 0, 400, 400)).copyTo(partImage);//拷贝矩阵，开辟内存 src.copyTo(dst)，将src复制到dst矩阵中

    //得到圈出ROI的Mat
    Mat ROIImage = srcImage(Rect(100, 100, 400, 400));

//Ptr
    //加载掩模,必须是灰度图或二值图
//    Mat mask;
//    cvtColor(partImage, mask, COLOR_BGRA2GRAY);//颜色转换成灰色的图像

//    //对roi子图像进行修改
//    Sobel(ROIImage,ROIImage,1,0,3);//src和dst为同一个
    //合并
    partImage.copyTo(ROIImage);//复制图像到另一个图像或矩阵上，可选参数是掩码

    jint *ptr = ROIImage.ptr<jint>(0);

    int size = w * h;

    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, ptr);//将ptr指向的srcImage对象像素数值赋值给result
    env->ReleaseIntArrayElements(buf_, cbuf, 0);
    return result;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_cmakedemo_MainActivity_outputString(JNIEnv *env, jobject thiz, jstring str) {
    //getStringUtfChars返回字符数组
   const char *msg = env->GetStringUTFChars(str,0);
   LogUtil logUtil;//定义类
   //调用函数
   logUtil.test(msg);

}