package com.mind.valley.mvil.manager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.net.MalformedURLException
import java.net.URL

object MvilLoadImageFetcher{
    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round(height.toFloat() / reqHeight.toFloat())
            } else {
                inSampleSize = Math.round(width.toFloat() / reqWidth.toFloat())
            }
        }
        return inSampleSize
    }

    fun decodeSampledBitmapFromDrawable(context: Context, res: Int, reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, res, options)

        // Calculate inSampleSize
        if (reqHeight != 0) {
            options.inSampleSize =
                calculateInSampleSize(options, reqWidth, reqHeight)
        }
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(context.resources, res, options)
    }

    fun decodeSampledBitmapFromUrl( strUrl: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        var url: URL? = null
        try {
            url = URL(strUrl)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        val input = url?.openStream()
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        if (reqWidth != 0 && reqHeight != 0) {
            options.inSampleSize =
                calculateInSampleSize(options, reqWidth, reqHeight)
        }
//             Decode Bitmap
        return BitmapFactory.decodeStream(input, null, options)


    }

}