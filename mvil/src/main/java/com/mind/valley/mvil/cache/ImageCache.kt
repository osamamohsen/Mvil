package com.irozon.mvil.cache

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.util.LruCache

/**
 * Created by Osama Mohsen.
 */
class ImageCache(maxSize: Int) : LruCache<String, Bitmap>(maxSize) {

    companion object {

        private var instance: ImageCache? = null

        operator fun get(context: Context?, cacheAllowed: Float): ImageCache {
            if (instance == null) {
                val am = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val memClassBytes = (am.memoryClass * 1024 * 1024).toFloat()
                val cacheSize = memClassBytes * cacheAllowed
                instance = ImageCache(Math.round(cacheSize))
            }
            return instance as ImageCache
        }
    }
}
