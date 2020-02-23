package com.example.cmakedemo.ui;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.cmakedemo.R;
import com.example.cppso.aviplayer.AviPlayer;
import com.example.cppso.utils.LogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Henry
 * @Date 2020-02-23  17:07
 * @Email 2427417167@qq.com
 */
public class AviPlayerActivity extends Activity {

    private RxPermissions rxPermissions;
    private String path = Environment.getExternalStorageDirectory().getPath()+"/"+ "drop.avi";
    private long mAvi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        listOf(10, 20, 30, 40)

        rxPermissions = new RxPermissions(this);
        setContentView(R.layout.opencv_surfaceview);
        Disposable no_permission = rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe((Boolean aBoolean) -> {
                    if (aBoolean) {
                        preparePlay();
                    } else {
                        Toast.makeText(AviPlayerActivity.this, "No Permission", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void preparePlay() {
        if (new File(path).exists()) {
            mAvi = AviPlayer.open(path);
            printAVIInfo();
        }
    }


    private void  printAVIInfo() {
        LogUtil.Companion.d("file path is "+path,"cppso");
        LogUtil.Companion.d("get avi id is "+path,"cppso");
        LogUtil.Companion.d("get avi width is " + AviPlayer.getWidth(mAvi),"cppso");
        LogUtil.Companion.d("get avi height is " + AviPlayer.getHeight(mAvi),"cppso");
        LogUtil.Companion.d("get avi frameRate is " + AviPlayer.getFrameRate(mAvi),"cppso");
    }
}
