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

