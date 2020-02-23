//
// Created by xiahangli on 2020-02-09.
//

#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_cppso_SampleNativeMethod_NativeString(JNIEnv *env, jclass clazz) {
//    javaVm.GetEnv()
    // env 的 newXXX 方法可以生成各种类型
    jstring nativeString = env->NewStringUTF("hello native string");
    return nativeString;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_cppso_SampleNativeMethod_NativeIntArray(JNIEnv *env, jclass clazz) {
    //得到java层数组的length,
    jintArray  ary = env->NewIntArray(10);
    jint size = env->GetArrayLength(ary);
//    j,intArray intArray = env->NewIntArray(size);
//指针类型
    jint *intArrayPointer = env->GetIntArrayElements(ary,JNI_FALSE);

    //循环加17
    for (int i = 0; i < size; i++)
        intArrayPointer[i] += i;

    //根据int数组个数来创建一个jintArray
    jintArray newIntArray = env->NewIntArray(size);
    //接下来就是把intArray数组的值都拷贝到新的newIntArray中，api是SetIntArrayRegion
    // (*SetIntArrayRegion)(JNIEnv*, jintArray,
    //                        jsize, jsize, const jint*);
    //也就是将intArrayPointer拷贝到newIntArray便于返回到java层
    env->SetIntArrayRegion(newIntArray,0,size,intArrayPointer);
    //返回到java层
    return newIntArray;
}