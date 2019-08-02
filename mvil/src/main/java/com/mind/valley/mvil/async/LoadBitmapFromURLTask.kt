package com.irozon.mvil.async

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.view.animation.*
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
    private val strUrl: String,
    private val mPlaceHolderBitmap: Bitmap?
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
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap)

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
                } else {
                    imageView.visibility = View.VISIBLE
                    if(mPlaceHolderBitmap != null)
                        imageView.setImageBitmap(mPlaceHolderBitmap)
                }
            }
        }
    }
}

