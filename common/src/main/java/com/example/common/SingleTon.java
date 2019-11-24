package com.example.common;

import android.content.Context;

/**
 * @User Xiahangli
 * @Date 2019-11-24  15:58
 * @Email henryatxia@gmail.com
 * @Descrip
 */
public class SingleTon {
    private Context context;
    private boolean isInit;

    private SingleTon() {

    }

    public  void init(Context ctx) {
        if (!isInit){
            context = ctx;
            isInit = true;
        }
    }

    private static final class HOLDER {
        public static final SingleTon instance = new SingleTon();
    }

    public static SingleTon obtain() {
        return HOLDER.instance;
    }




}
