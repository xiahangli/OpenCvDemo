package com.example.cmakedemo.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmakedemo.base.BaseActivity
import com.example.cmakedemo.nativefactory.NativeEngine
import com.example.cmakedemo.viewholder.SampleBinder
import com.example.common.databinding.DataBindingAdapter
import com.example.common.databinding.Items
import com.example.cmakedemo.viewholder.SampleModel
import com.example.common.utils.NATIVE_INT_ARRAY
import com.example.common.utils.NATIVE_STRING
import kotlinx.android.synthetic.main.opencv_activity_base.*

class NativeOperationActivity : BaseActivity() {

    val items = Items()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        NativeEngine.pringlog()//只是为了类加载，调用static块
        initData()

    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager
        val mAdapter = DataBindingAdapter()

        mAdapter.map(SampleModel::class.java, SampleBinder()).setItems(items)

        mRecyclerView.adapter = mAdapter

    }


    private fun initData() {
        items.add(SampleModel("getNativeString", NATIVE_STRING))
        items.add(SampleModel("getNativeIntArray", NATIVE_INT_ARRAY))
        items.add(SampleModel("a"))
        items.add(SampleModel("a"))
        items.add(SampleModel("a"))
        items.add(SampleModel("a"))
    }
}
