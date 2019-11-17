//#include<opencv2/opencv.hpp>
//#include<iostream>
//#include<math.h>
//#include <jni.h>
//
//using namespace std;
//using namespace cv;
//
//extern "C"  Mat grayImage(const Mat& src);//, Mat &dst
//
//
//extern "C"
//JNIEXPORT jintArray JNICALL
// Java_com_alibaba_imageprocess_GrayUtil_grayImage(const Mat& src) {//, Mat &dst
//
//	Mat gray_src, dst;
//	//if (src.empty()) {
//	//	cout << "could not load image..." << endl;
//	//	return ;//-1
//	//}
//	cvtColor(src, gray_src, CV_BGR2GRAY);//����������˻Ҷ�ת��
//
//	//��ͨ��
//
//	//printf("channels: %d\n", nc);
//
//	for (int row = 0; row < height; row++) {
//		for (int col = 0; col < width; col++) {
//			if (nc == 1) {
//				int gray = gray_src.at<uchar>(row, col);
//				gray_src.at<uchar>(row, col) = gray;//255 -
//			}
//			else if (nc == 3) {//
//				int b = src.at<Vec3b>(row, col)[0];
//				int g = src.at<Vec3b>(row, col)[1];
//				int r = src.at<Vec3b>(row, col)[2];
//				/*dst.at<Vec3b>(row, col)[0] = b;
//				dst.at<Vec3b>(row, col)[1] = g;
//				dst.at<Vec3b>(row, col)[2] = 0;*/
//				gray_src.at < uchar >(row, col) = (r * 76 + g * 150 + b * 30) >> 8;//max(r, max(g, b))
//			}
//		}
//	}
//	//imwrite("D:/test01.jpg", gray_src);
//	return gray_src;
//}