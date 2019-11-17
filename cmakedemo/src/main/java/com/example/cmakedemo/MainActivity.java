package com.example.cmakedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("native-lib");
    }

    private GLSurfaceView mGlsurfaceVIew;

    public native String getC();

    public native int[] getGray(int buf[], int w, int h);


    public native int[] roi(int buf[], int w, int h);

    private ImageView mImageView;
    private ImageView imageView1;
    private ImageView imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image_view);
        imageView1 = findViewById(R.id.image_view1);
        imageView2 = findViewById(R.id.image_view2);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.test)).getBitmap();
        int w = bitmap.getWidth(), h = bitmap.getHeight();
        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        int[] resultPixels = getGray(pix,w,h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        result.setPixels(resultPixels, 0, w, 0, 0, w, h);
        Matrix matrix = new Matrix();
//        matrix.postScale()

        mImageView.setImageBitmap(result);
//
//        imageView1.setImageBitmap(result);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
//                        R.drawable.test)).getBitmap();
//                int w = bitmap.getWidth(), h = bitmap.getHeight();
//                int[] pix = new int[w * h];
//                bitmap.getPixels(pix, 0, w, 0, 0, w, h);
//                int[] resultPixels = getGray(pix,w,h);
//                Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
//                result.setPixels(resultPixels, 0, w, 0, 0,w, h);
//                mImageView.setImageBitmap(result);
//            }
//        },2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
