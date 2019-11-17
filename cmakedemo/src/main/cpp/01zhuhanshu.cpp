#include<opencv2/opencv.hpp>
//#include<iostream>
//#include<math.h>
#include<02gray.h>
using namespace std;
using namespace cv;


int main(int argc, char** argv)
{
	Mat src1, dst1, gray_src1;
	src1 = imread("D:/meinv001.jpg");
	if (src1.empty()) {
		cout << "could not load image..." << endl;
		return -1;
	}
	namedWindow("input", CV_WINDOW_AUTOSIZE);
	imshow("input", src1);

	long st = getTickCount();//获取时间戳
							 //对图片进行处理
	gray_src1 = grayImage(src1);//, Mat &dst

	namedWindow("output", CV_WINDOW_AUTOSIZE);
	imshow("output", gray_src1);
	//计算显示时间
	long tt = getTickCount() - st;
	float time = (tt / getTickFrequency()) * 1000;
	printf("execution time(ms) : %.2f\n", time);

	//等待
	waitKey(0);
	return 0;
}