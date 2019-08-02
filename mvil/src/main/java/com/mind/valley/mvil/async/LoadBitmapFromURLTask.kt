package com.irozon.mvil.async

import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView

import com.irozon.mvil.cache.ImageCache
import com.irozon.mvil.manager.ImageFetcher
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import java.net.MalformedURLException
import java.net.URL
import android.view.animation.Animation



/**
 * Created by Osama Mohsen.
 */
class LoadBitmapFromURLTask(
    private val imageView: ImageView,
    private val mCache: ImageCache?,
    private val mCacheImage: Boolean,
    private val mWidth: Int,
    private val mHeight: Int,
    private val strUrl: String
) {

    fun execute() {
        doAsync {
            var url: URL? = null
            try {
                url = URL(strUrl)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            if (url != null) {
                val bitmap = ImageFetcher.decodeSampledBitmapFromUrl(url, mWidth, mHeight)
                uiThread {
                    if (mCacheImage) {
                        mCache?.put(strUrl, bitmap)
                    }
                    imageView.visibility = View.VISIBLE

                    val fadeOut = AlphaAnimation(0f, 1f)
                    fadeOut.interpolator = AccelerateInterpolator()
                    fadeOut.duration = 1000
                    imageView.startAnimation(fadeOut)


                    imageView.setImageBitmap(bitmap)

                }
            }
        }
    }
}

