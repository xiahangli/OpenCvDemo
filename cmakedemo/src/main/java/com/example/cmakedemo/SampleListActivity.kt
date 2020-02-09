package com.example.cmakedemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmakedemo.bean.NativeCrashSample
import com.example.cmakedemo.bean.SampleItem
import kotlinx.android.synthetic.main.opencv_activity_sample_list.*
import java.util.*

class SampleListActivity : AppCompatActivity() {

    private lateinit var mAdapter: SampleListAdapter

    private lateinit var mListView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opencv_activity_sample_list)
        mListView = sample_list
        mAdapter = SampleListAdapter()
        mListView.layoutManager = LinearLayoutManager(this)
        mListView.setHasFixedSize(true)
        mListView.adapter = mAdapter
    }
}


class SampleListAdapter : RecyclerView.Adapter<ItemViewHolder>() {


    private var mItems: ArrayList<SampleItem> = ArrayList()

    init {
        //构造一个对象，并添加到list中，其中第二个参数为func 指定为NativeCrashSample()对象
        mItems.add(SampleItem("title", func = NativeCrashSample()))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.opencv_sample_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

}


class ItemViewHolder(var root: View) : RecyclerView.ViewHolder(root) {

    var text: TextView = root.findViewById(R.id.item_text)

    fun bind(data: SampleItem) {
        text.text = data.title
        text.setOnClickListener(data.func)
    }
}



