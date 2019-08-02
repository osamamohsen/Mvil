package com.irozon.mvil.async

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.irozon.mvil.cache.ImageCache
import com.irozon.mvil.manager.ImageFetcher
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.view.animation.AnimationSet
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator



/**
 * Created by Osama Mohsen.
 */
class LoadBitmapFromDrawableTask(private val mContext: Context?, private val imageView: ImageView, private val mCache: ImageCache?, private val mCacheImage: Boolean, private val mWidth: Int, private val mHeight: Int, var res: Int) {
    fun execute() {
        doAsync {
            val bitmap = mContext?.let { ImageFetcher.decodeSampledBitmapFromDrawable(it, res, mWidth, mHeight) }
            uiThread {
                if (mCacheImage) {
                    bitmap?.let { it1 -> mCache?.put(res.toString(), it1) }
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

