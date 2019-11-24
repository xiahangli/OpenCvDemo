package com.example.common;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.example.common.dynamicproxy.IOperation;
import com.example.common.dynamicproxy.OperationImpl;
import com.example.common.dynamicproxy.TimingProxy;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

/**
 * @User Xiahangli
 * @Date 2019-11-24  15:44
 * @Email henryatxia@gmail.com
 * @Descrip
 */
public enum  CommonApplication implements ICommonProxy  {
    APP,APP1;

    private Application application;
    @Override
    public void onCreate(Application app) {
        if (app!=null){
            //注入自定义的初始化到真实的application中
            application=app;
            SingleTon.obtain().init(app);
            TimingProxy proxy = new TimingProxy();
            proxy.setProxy(new OperationImpl());
            @SuppressWarnings("unchecked")
            IOperation operation = proxy.create(IOperation.class);

            Integer add = operation.add(2, 10);
            Log.i("TimingProxy",add+"");
        }
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onLowMemory(int level) {

    }

    @Override
    public void attach() {

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }
}
