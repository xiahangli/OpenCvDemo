package com.example.cmakedemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.cmakedemo.aop.ContentView;
import com.example.cmakedemo.aop.InjectUtil;
import com.example.cmakedemo.aop.InjectView;
import com.example.cmakedemo.aop.OnClick;
import com.example.cmakedemo.base.BaseActivity;

//注解的方式设置contentView
@ContentView(R.layout.opencv_activity_main)
public class MainActivity extends BaseActivity {

    static {
        System.loadLibrary("native-lib");
    }

    private int a;

    public native String getC();

    public native int[] getGray(int buf[], int w, int h);


    public native int[] roi(int buf[], int w, int h);

    @InjectView(R.id.image_view)
    private ImageButton imageView;

    @InjectView(R.id.button)
    private Button button;


//    @OnLongClick({R.id.btn, R.id.tv})
//    public boolean longClick(View btn) {
//        switch (btn.getId()) {
//            case R.id.btn:
//                Toast.makeText(this, "btn longClick", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.tv:
//                Toast.makeText(this, "tv longClick", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @OnClick(value = {R.id.button, R.id.image_view}) // 有可能注解值是没有控件注入赋值的
//    方法名可以随意定义
    public boolean click(View view) {
        System.out.println("==========" + view.toString());
//        Toast.makeText(this, "textView", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.button:
                System.out.println("========" + R.id.button);
                System.out.println("=========" + view.getId());
                Toast.makeText(this, "tv click", Toast.LENGTH_SHORT).show();
                a++;
//                startActivity(new Intent(this, RViewActivity.class));
                break;

            case R.id.image_view:
                System.out.println("========" + R.id.image_view);
                System.out.println("=========" + view.getId());
                Toast.makeText(this, "image", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opencv_activity_main);
//        InjectUtil.injectLayout(this);
        outputString("ste");
//        InjectUtil.injectView(this);
        //todo 如果这条语句放在InjectUtil.injectEvents(this)之后，则不会通过反射回调，因为反射的监听器被这条语句覆盖了，
        // 同理，下面这条语句放在反射 InjectUtil.injectEvents(this);之前，则这条语句不起作用，因为被injectEvents覆盖
        imageView = findViewById(R.id.image_view);
        View Btm = findViewById(R.id.button);
        Btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    a=a/0;
                    System.out.println();
                    a++;
                    if (a == 2) {
                        System.out.println();
                    }
                    throw new IllegalArgumentException();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("=======");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(
                        "dasfasdf"
                );
            }
        });
//        InjectUtil.injectEvents(this);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.opencv_test)).getBitmap();
        int w = bitmap.getWidth(), h = bitmap.getHeight();
        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        int[] resultPixels = getGray(pix, w, h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        result.setPixels(resultPixels, 0, w, 0, 0, w, h);
        imageView.setImageBitmap(result);


    }


    public native void outputString(String str);

    public native void printStr();

}
