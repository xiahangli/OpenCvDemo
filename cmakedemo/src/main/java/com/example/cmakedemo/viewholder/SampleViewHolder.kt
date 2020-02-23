package com.example.cmakedemo.viewholder

import com.example.cmakedemo.databinding.OpencvSampleTypeBinding
import com.example.common.databinding.BindingViewHolder

class SampleViewHolder(binding: OpencvSampleTypeBinding?) : BindingViewHolder<SampleModel, OpencvSampleTypeBinding>(binding) {


    override fun onBind(data: SampleModel?, position: Int) {
        mBinding.model = data!!
        mBinding.executePendingBindings()
        mBinding.eventhandler = EventHandler()
    }
}