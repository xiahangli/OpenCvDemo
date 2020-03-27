package com.example.cmakedemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cmakedemo.R
import com.example.cmakedemo.databinding.OpencvSampleTypeBinding
import com.example.common.databinding.ViewHolderBinder


class SampleBinder : ViewHolderBinder<SampleModel, SampleViewHolder>() {


    override fun onBindViewHolder(holder: SampleViewHolder?, data: SampleModel?, position: Int) {
        holder!!.onBind(data,position)
    }

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?): SampleViewHolder {
        val mBinding = DataBindingUtil.inflate(inflater, R.layout.opencv_sample_type,parent,false) as OpencvSampleTypeBinding
        return SampleViewHolder(mBinding)
    }
}