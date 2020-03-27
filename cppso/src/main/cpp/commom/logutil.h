//
// Created by xiahangli on 2020-02-23.
//

//#include <android/log.h>

#ifndef OPENCVDEMO_LOGUTIL_H
#define OPENCVDEMO_LOGUTIL_H

#define LOGTAG "native-lib"

#define LOGD(...) = __android_log_print(ANDROID_LOG_DEBUG,LOGTAG,__FORMAT__)
#define LOGE(...) = __android_log_print(ANDROID_LOG_DEBUG,LOGTAG,__VA_ARGS__)
#define LOGi(...) = __android_log_print(ANDROID_LOG_DEBUG,LOGTAG,__VA_ARGS__)


#endif //OPENCVDEMO_LOGUTIL_H
