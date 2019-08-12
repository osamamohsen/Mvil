package com.mind.valley.mvil

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.AnyRes
import android.support.annotation.DrawableRes
import android.util.Log
import android.view.View
import android.widget.ImageView

import com.irozon.mvil.cache.ImageCache
import com.irozon.mvil.manager.PlaceHolder
import com.mind.valley.mvil.async.DownloadDrawableTask
import com.mind.valley.mvil.async.DownloadFromUrlTask
import java.lang.ref.WeakReference
import kotlin.let


/**
 * Created by Osama Mohsen.
 */

class Mvil {
    private var mUrl: String? = null
    private var enableCache = true
    private var mRes: Int = 0
    private var mPlaceHolderBitmap: Bitmap? = null
    private var mWidth = DEFAULT_WIDTH
    private var mHeight = DEFAULT_HEIGHT
    private var cacheAllowed = 1f

    companion object {
        private var contextWeakReference: WeakReference<Context>? = null
        private lateinit var mvilWeakReference: WeakReference<Mvil>
        private var DEFAULT_WIDTH = 500
        private var DEFAULT_HEIGHT = 500
        private var cacheMaxCapacity: Float = 0f

        fun with(context: Context): Mvil {

            contextWeakReference = WeakReference(context)

            cacheMaxCapacity =
                ((context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).memoryClass * 1024 * 1024).toFloat()

            val mvil = Mvil()
            mvilWeakReference = WeakReference(mvil)

            DEFAULT_HEIGHT = Utils.getScreenHeight(getContext())!!
            DEFAULT_WIDTH = Utils.getScreenWidth(getContext())!!

            return getMiva()
        }

        private fun getContext(): Context? {
            return contextWeakReference?.get()
        }


        private fun getMiva(): Mvil {
            return mvilWeakReference.get()!!
        }
    }

    /**
     * Image source by url
     * @param url
     */
    fun source(url: String): Mvil {
        mUrl = url
        mRes = -100
        return getMiva()
    }

    /**
     * Image source by drawable resource
     * @param res
     */
    fun source(@DrawableRes res: Int): Mvil {
        mRes = res
        mUrl = null
        return getMiva()
    }

    /**
     * Resize image
     * @param width
     * @param height
     */
    fun resize(width: Int, height: Int): Mvil {
        if (width != 0)
            mWidth = width
        if (height != 0)
            mHeight = height
        return getMiva()
    }

    /**
     * Placeholder image
     * @param placeholder - Drawable
     */
    fun placeholder(@AnyRes placeholder: Int): Mvil {
        if (placeholder != PlaceHolder.placeHolder) {
            try {
                // Try for drawable resource
                Log.e("placeHolder","start");
                mPlaceHolderBitmap = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(
                        getContext()?.resources,
                        placeholder
                    ), 300, 300, true
                )
                PlaceHolder.placeHolder = placeholder
                PlaceHolder.placeHolderBitmap = mPlaceHolderBitmap
                PlaceHolder.placeHolderColor = -1
                Log.e("placeHolder","done");
            } catch (ignored: Exception) {
                Log.e("placeHolder","ignored"+ignored.message);
                PlaceHolder.placeHolder = placeholder
                PlaceHolder.placeHolderBitmap = null
                PlaceHolder.placeHolderColor = placeholder
            }

        } else {
            Log.e("placeHolder","null");
            mPlaceHolderBitmap = PlaceHolder.placeHolderBitmap
        }
        return getMiva()
    }

    fun setMaxCapacityCache(float: Float): Mvil {
        cacheMaxCapacity = float
        return getMiva()
    }

    /**
     * Enable cache and set cache size - 0 to 1f
     * @param percent
     */
    fun enableCache(percent: Float): Mvil {
        enableCache = true
        cacheAllowed = percent
        return getMiva()
    }

    /**
     * Disable cache
     */
    fun disableCache(): Mvil {
        enableCache = false
        cacheAllowed = 0f
        return getMiva()
    }

    /**
     * Load image to an ImageView
     * @param imageView
     */
    fun loadImage(imageView: ImageView) {
        imageView.visibility = View.GONE
        var imageCache: ImageCache? = null
        var bitmap: Bitmap? = null
        if (enableCache) {
            /*Get imageCache instance*/
            imageCache = ImageCache[getContext(), cacheAllowed]
            var imageKey: String? = null
            if (mUrl != null) { //that mean it is loaded from url
                imageKey = mUrl
            }
            if (mRes != -100) { //that mean it is loaded from resources
                imageKey = mRes.toString()
            }
            bitmap = imageKey?.let { imageCache.get(it) }
        }
        if (bitmap != null) {
            imageView.visibility = View.VISIBLE
            imageView.setImageBitmap(bitmap)
        } else {
            if (mUrl != null) { /*Load using url*/

                if(mPlaceHolderBitmap == null)
                    Log.e("osama_place","null")
                else
                    Log.e("osama_place","not null")

                val task = DownloadFromUrlTask(
                    imageView,
                    imageCache,
                    enableCache,
                    mWidth,
                    mHeight,
                    mUrl!!,
                    mPlaceHolderBitmap
                )

                // Set Placeholder
                if (PlaceHolder.placeHolderBitmap != null) { // Placeholder is bitmap
                    imageView.setImageBitmap(PlaceHolder.placeHolderBitmap)
                } else if (PlaceHolder.placeHolderColor != -1) { // Placeholder is color
                    imageView.setImageResource(PlaceHolder.placeHolderColor)
                }

                task.execute()
            }
            if (mRes != -100) { /*Load using Drawable resource*/
                val task = DownloadDrawableTask(
                    getContext(), imageView, imageCache,
                    enableCache, mWidth, mHeight, mRes
                )

                // Set Placeholder
                if (PlaceHolder.placeHolderBitmap != null) { // Placeholder is bitmap
                    imageView.setImageBitmap(PlaceHolder.placeHolderBitmap)
                } else if (PlaceHolder.placeHolderColor != -1) { // Placeholder is color
                    imageView.setImageResource(PlaceHolder.placeHolderColor)
                }

                task.execute()
            }
        }
    }
}