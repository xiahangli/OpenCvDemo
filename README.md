## OpencvDemo ##

**cmakedemo是根据cmake构建系统编写的一个基于opencv的测试demo**


### 主要功能 ###
- 实现图像的灰度化


### 使用步骤 ###
step1:官网下载最近的[opencv4](https://sourceforge.net/projects/opencvlibrary/files/4.0.1/opencv-4.0.1-android-sdk.zip/download)
<br />



step2：修改cmakedemo/src/main/cpp/CMakeLists.txt中
```
    set(pathToOpenCv  /Users/henry/Downloads/OpenCV-android-sdk)
```
/Users/henry/Downloads/OpenCV-android-sdk替换成自己的官网下载的opencv4的根目录

```
set_target_properties(lib_opencv 
              PROPERTIES  
              IMPORTED_LOCATION   
              /Users/henry/Downloads/LittleDrawBoard_AN-master/cmakedemo/src/main/jniLibs/arm64-v8a/${}/libopencv_java4.so
              )
```
/Users/henry/Downloads/LittleDrawBoard_AN-master/cmakedemo/src/main/jniLibs/arm64-v8a/${}/libopencv_java4.so替换成本地libopencv_java4.so文件的地址
                            
## 联系 ##
2427417167@qq.com
