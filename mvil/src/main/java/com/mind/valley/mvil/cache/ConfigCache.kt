package com.irozon.mvil.cache

import android.util.Log

/**
 * Created by Osama Mohsen.
 */
object ConfigCache {
    fun setMemCacheSizePercent(percent: Float): Float {
        if (percent > 1f) {
            Log.w("Cache size warning", "Cache should be less than 1(100%). Setting 25%")
            return 25f
        }
        return percent
    }
}
