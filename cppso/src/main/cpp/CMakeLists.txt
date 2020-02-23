cmake_minimum_required(VERSION 3.4.1)

add_library( # Sets the name of the library.
        cppso

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${CMAKE_SOURCE_DIR}/native-lib.cpp )

message(${CMAKE_SOURCE_DIR})
SET(VAR HELL)
message(${VAR})





#第二个库
add_library(

        native-operation

        SHARED

        ${CMAKE_SOURCE_DIR}/native-operation.cpp )

#多媒体播放
add_library(
        player
        SHARED
        ${CMAKE_SOURCE_DIR}/aviplayer/player.cpp
)

#avi基础库
add_library(
        avilib
        SHARED
        ${CMAKE_SOURCE_DIR}/avilib/avilib.c
        ${CMAKE_SOURCE_DIR}/avilib/platform_posix.c
)

#添加到搜索路径中
include_directories(${CMAKE_SOURCE_DIR}/avilib/)
include_directories(${CMAKE_SOURCE_DIR}/commom/)

add_library(
        common
        SHARED
        ${CMAKE_SOURCE_DIR}/commom/commonutils.cpp
)



find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

target_link_libraries(# Specifies the target library.
        native-operation

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )

target_link_libraries( # Specifies the target library.
        avilib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )

#链接生成player的so库,需要将avilib链接到player中，不然报未定义
#指向avilib中的函数的引用，即undefined reference to AVI_video_height(avi_t*)
target_link_libraries(
        player
        common
        avilib
        ${log-lib}
)

target_link_libraries( # Specifies the target library.
        cppso

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )