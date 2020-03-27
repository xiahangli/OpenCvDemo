package com.example.cmakedemo.viewholder

import android.view.View
import com.example.common.utils.NATIVE_INT_ARRAY
import com.example.common.utils.NATIVE_STRING
import com.glumes.cppso.NativeOperation

class EventHandler {

    fun onClick(view: View, model: SampleModel) {
        when (model.type) {
            NATIVE_STRING -> NativeOperation.getNativeString()
            NATIVE_INT_ARRAY -> NativeOperation.getNativeIntArray()
        }
    }
}