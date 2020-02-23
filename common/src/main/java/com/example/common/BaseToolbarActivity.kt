package com.example.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_base_toolbar.*

/**
 * @author Henry
 *@Date 2020-02-09  12:28
 *@Email 2427417167@qq.com
 */
abstract class BaseToolbarActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设定activity的主题
        setTheme(R.style.BaseContainer)
        setContentView(R.layout.activity_base_toolbar)
        //别忘了导入kotlin-android-extensions,紫烈重写setUpToolbar
        setUpToolbar(mToolbar)
        setSupportActionBar(mToolbar)
    }


    override fun setContentView(layoutResID: Int) {
        if (layoutResID == R.layout.activity_base_toolbar) {
            super.setContentView(layoutResID)
        } else {
            setContent(layoutResID)
        }
    }


    override fun setContentView(view: View) {
        mContainer.removeAllViews()
        mContainer.addView(view)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        mContainer.removeAllViews()
        mContainer.addView(view, params)
    }

    private fun setContent(layoutResID: Int) {
        LayoutInflater.from(this).inflate(layoutResID, mContainer)
    }


    open fun setUpToolbar(toolbar: Toolbar){}
}