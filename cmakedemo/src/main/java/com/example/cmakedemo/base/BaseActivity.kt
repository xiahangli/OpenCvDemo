package com.example.cmakedemo.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.cmakedemo.R
import com.example.common.BaseToolbarActivity

/**
 * @author Henry
 *@Date 2020-02-09  12:24
 *@Email 2427417167@qq.com
 */
open class BaseActivity :BaseToolbarActivity(){

    //必须提供布局文件，否则找common库中
    //会出问题
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opencv_activity_base)
    }

    //定制toolbar
    override fun setUpToolbar(toolbar: Toolbar) {
        //设置标题
        toolbar.title = getString(R.string.app_name)
    }
}