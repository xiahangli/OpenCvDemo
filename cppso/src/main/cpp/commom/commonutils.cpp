//
// Created by xiahangli on 2020-02-23.
//

#include <jni.h>
#include "commonutils.h"

//commonutils类的抛出异常的实现
//void commonutils::throwmsgbyname(JNIEnv *env, const char name, const char *msg) {
//    //1.找到类
//    jclass clz = env->FindClass(&name);
//    //2.调用核心的ThrowNew，在c++层抛出java的异常
//    if(clz!=NULL){
//        env->ThrowNew(clz,msg);
//    }
//    //3.释放开辟的local内存区域DeleteXXXReference
//    env->DeleteLocalRef(clz);
//}
