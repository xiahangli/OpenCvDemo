//
// Created by xiahangli on 2020-02-23.
//

#ifndef OPENCVDEMO_PLAYER_H
#define OPENCVDEMO_PLAYER_H

//定义了c++的代码段，需要加上extern C ,c++为了支持函数重载，需要对函数名进行处理（加入返回值等）而c不会加入扩展信息
#include <jni.h>
#include <logutil.h>
#include <avilib.h>

#ifdef __cplusplus
extern "C" {

#endif

//todo
JNIEXPORT jlong JNICALL
Java_com_example_cppso_aviplayer_AviPlayer_open(JNIEnv *, jclass, jstring);

JNIEXPORT jlong JNICALL Java_com_example_cppso_aviplayer_AviPlayer_init(JNIEnv *, jclass, jlong);

JNIEXPORT jint JNICALL
Java_com_example_cppso_aviplayer_AviPlayer_getHeight(JNIEnv *, jclass, jlong);

JNIEXPORT jdouble JNICALL
Java_com_example_cppso_aviplayer_AviPlayer_getFrameRate(JNIEnv *,jclass,jlong );

#ifdef __cplusplus
};

#endif


#endif //OPENCVDEMO_PLAYER_H
