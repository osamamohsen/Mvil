package com.mind.valley.mvil

import android.content.Context

/**
 * Created by hammad.akram on 3/1/18.
 */

object Utils {

    // get screen height to set default image height
    fun getScreenHeight(context: Context?): Int? {
        val dm = context?.resources?.displayMetrics
        return dm?.heightPixels?.minus(getStatusBarHeight(context))
    }

    // get screen width to set default image width
    fun getScreenWidth(context: Context?): Int? {
        val dm = context?.resources?.displayMetrics
        return dm?.widthPixels
    }


    // get status bar height
    fun getStatusBarHeight(context: Context?): Int {
        var result = 0
        val resourceId = context?.resources?.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId != null) {
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }

}
