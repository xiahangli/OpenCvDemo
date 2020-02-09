//
//todo 在cmakeLists.txt中添加 add_library(native-lib
//        SHARED
//        native-lib.cpp
//        native-lib.cpp)
//

#include "LogUtil.h"
#include <string>
#include <android/log.h>
//宏定义一个TAG标签，可以在LogCat中输入NativeCodec-looper观察日志
#define TAG "NativeCodec-looper"
//定义函数简写，每次低啊用LOGD的时候就是调用__android_log_print这个函数
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

using namespace std;

LogUtil::LogUtil() {

}

//测试方法，c++语法LogUtil::代表作用域为LogUtil类
void LogUtil::test(string msg) {
    LOGD("this is content two");
}


