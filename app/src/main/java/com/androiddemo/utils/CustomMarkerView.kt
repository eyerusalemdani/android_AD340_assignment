package com.androiddemo.utils

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.androiddemo.R

class CustomMarkerView(
    root: ViewGroup
) : FrameLayout(root.context) {

    init {
        View.inflate(context, R.layout.marker, this)
    }
}
