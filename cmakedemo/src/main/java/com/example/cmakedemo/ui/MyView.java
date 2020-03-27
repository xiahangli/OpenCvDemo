package com.example.cmakedemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Henry
 * @Date 2020-02-22  22:41
 * @Email 2427417167@qq.com
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("================="+event.getX()+","+event.getY());
        switch (event.getAction()){
        }
        return true;
    }
}
