//
// Created by glumes on 2017/6/4.
//

/**
 * C file Content
 * 注意这里需要在CmakeLists.txt的代码中添加add_library表示将其作为cpp文件
 * 此时c_lib的目录标蓝
 */
#include <jni.h>
#include <android/log.h>

#define TAG "CONFIGTEST"
#define LOGE(format, ...)  __android_log_print(ANDROID_LOG_ERROR, TAG, format, ##__VA_ARGS__)
#define LOGD(format, ...)  __android_log_print(ANDROID_LOG_DEBUG, TAG, format, ##__VA_ARGS__)


