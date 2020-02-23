cmake_minimum_required(VERSION 3.4.1)


#支持-std=gnu++11
set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

#设置opencv的sdk目录，/Users/henry/Downloads/OpenCV-android-sdk替换成个人在opencv官网下载到本地的sdk路径
set(pathToOpenCv /Users/henry/Downloads/OpenCV-android-sdk)#set指令自定义变量
#配置加载native依赖（头文件）
include_directories(${pathToOpenCv}/sdk/native/jni/include)#
#include_directories(${pathToOpenCv}/sdk/native/jni/include)


add_library(opencv_png STATIC IMPORTED)
#引入libopencv_java4.so文件

set_target_properties(opencv_png
        PROPERTIES
        IMPORTED_LOCATION /Users/henry/Desktop/OpenCvDemo/cmakedemo/src/main/jniLibs/x86/libopencv_java4.so#这里替换成你自己的在项目中jniLibs中的so库绝对路径地址
        )

#add_library表示添加一个库，native-lib是库名，cmake将其打包成libnative-lib.so文件
add_library(native-lib
        SHARED
        include/lib_log/LogUtil.cpp
        native-lib.cpp)#将native-lib.cpp 02gray.cpp等多个cpp文件编译成libnative-lib.so共享对象，第二个参数SHARED就是共享的意思
include_directories(include/)

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)


add_library(
        # Sets the name of the library.
        c-lib
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
       include/c_lib/c_mem.c
       include/c_lib/c_file.c
)

#最后将opencv静态库链接进native-lib中
target_link_libraries( # Specifies the target library.
        native-lib#打包进apk的库
#        ${log-lib}
        opencv_png#添加opencv库
        )