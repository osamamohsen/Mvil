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
                    val fadeIn = AlphaAnimation(0f, 1f)
                    fadeIn.interpolator = DecelerateInterpolator() //add this
                    fadeIn.duration = 1000

                    val fadeOut = AlphaAnimation(1f, 0f)
                    fadeOut.interpolator = AccelerateInterpolator() //and this
                    fadeOut.startOffset = 1000
                    fadeOut.duration = 1000

                    val animation = AnimationSet(false) //change to false
                    animation.addAnimation(fadeIn)
                    animation.addAnimation(fadeOut)
                    imageView.setAnimation(animation);

                    imageView.setImageBitmap(bitmap)

                }
            }
        }
    }
}

