package com.example.cmakedemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cmakedemo.aop.ContentView;
import com.example.cmakedemo.aop.InjectUtil;
import com.example.cmakedemo.aop.InjectView;
import com.example.cmakedemo.aop.OnClick;
import com.example.common.BaseActivity;

//注解的方式设置contentView
@ContentView(R.layout.opencv_activity_main)
public class MainActivity extends BaseActivity {

    static {
        System.loadLibrary("native-lib");
    }

    public native String getC();

    public native int[] getGray(int buf[], int w, int h);


    public native int[] roi(int buf[], int w, int h);

    @InjectView(R.id.image_view)
    private ImageView imageView;

    @InjectView(R.id.button)
    private Button button;


    @OnClick(value = {R.id.button, R.id.image_view}) // 有可能注解值是没有控件注入赋值的
//    方法名可以随意定义
    public boolean click(View view) {
        System.out.println("=========="+ view.toString());
//        Toast.makeText(this, "textView", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.button:
                System.out.println("========"+R.id.button);
                System.out.println("========="+view.getId());
                Toast.makeText(this, "tv click", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, RViewActivity.class));
                break;

            case R.id.image_view:
                System.out.println("========"+R.id.image_view);
                System.out.println("========="+view.getId());
//                Toast.makeText(this, "tv click", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtil.injectLayout(this);
        InjectUtil.injectView(this);
        InjectUtil.injectEvents(this);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.opencv_test)).getBitmap();
        int w = bitmap.getWidth(), h = bitmap.getHeight();
        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        int[] resultPixels = getGray(pix, w, h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        result.setPixels(resultPixels, 0, w, 0, 0, w, h);
        imageView.setImageBitmap(result);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
