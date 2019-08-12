package com.mind.valley.mvil.manager

import android.graphics.Bitmap
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.widget.ImageView

object MvilAnimation {
    fun fade(imageView: ImageView, duration: Long) {
        val fadeOut = AlphaAnimation(0f, 1f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = duration
        imageView.startAnimation(fadeOut)
    }
}