//
// Created by xiahangli on 2020-02-23.
//

#include "player.h"
#include <jni.h>
#include <stdlib.h>
#include <GLES2/gl2.h>
#include <avilib.h>
#include <commonutils.h>

struct Instance {
    char *buffer;
    GLuint  texture;

    //带构造函数的结构体
    //:后面的内容是初始化成员列表，即初始化buffer和纹理texture
    Instance() : buffer(0) ,texture(0){

    }

};

JNIEXPORT jlong JNICALL
Java_com_example_cppso_aviplayer_AviPlayer_open(JNIEnv *env, jclass clazz, jstring filename) {
    avi_t *avi = 0;
    //jni吧java对象当做指针传入本地方法中，指针指向jvm内部的数据结构，内部数据结构在内存的存储方式是不可见的
    //本地代码必须通过在jnienv中选择合适的jni函数操作jvm中的对象，如对于java.lang.string对应的jni类型是jstring,但是
    //只能通过GetStringUTFChars访问字符串内容
    env->GetStringUTFChars(filename,NULL);
    return (jlong )avi;
}

JNIEXPORT jint JNICALL Java_com_example_cppso_aviplayer_AviPlayer_getHeight(JNIEnv *env, jclass clazz, jlong avi) {
    return AVI_video_height((avi_t *) avi);
}

JNIEXPORT jdouble JNICALL
Java_com_example_cppso_aviplayer_AviPlayer_getFrameRate(JNIEnv *env,jclass,jlong avi){
    return AVI_frame_rate((avi_t *) avi);
}



JNIEXPORT jlong Java_com_example_cppso_aviplayer_AviPlayer_init(JNIEnv *env,jclass,jlong avi){
    //1. new 是动态申请，不释放出了作用域照样行，而栈区就要自动释放。
    //2. new 可以申请的空间很大，而栈区就很小了，如果楼主是申请大空间尤其有用
    //3.new有两步，第一步申请空间，第二调用构造函数
    //4.new必须要delete，否则内存泄漏
    //new 返回的是对象的首地址，指针接收
    Instance* instance = new Instance();

    //1.先找到帧的size

    AVI_frame_size(avi,)
    return (jlong)instance;
}