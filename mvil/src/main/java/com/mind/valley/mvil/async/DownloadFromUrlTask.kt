package com.mind.valley.mvil.async

import android.graphics.Bitmap
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.irozon.mvil.cache.ImageCache
import android.view.View
import com.mind.valley.mvil.manager.MvilLoadImageFetcher
import com.mind.valley.mvil.manager.MvilAnimation


/*
    private val imageView: ImageView,
    private val mCache: ImageCache?,
    private val mCacheImage: Boolean,
    private val mWidth: Int,
    private val mHeight: Int,
    private val strUrl: String,
    private val mPlaceHolderBitmap: Bitmap?
 */
class DownloadFromUrlTask(
    private val imageView: ImageView,
    private val mCache: ImageCache?,
    private val mCacheImage: Boolean,
    private val mWidth: Int,
    private val mHeight: Int,
    private val strUrl: String,
    private val mPlaceHolderBitmap: Bitmap?
) : AsyncTask<Void, Void, Bitmap>() {

    var error: Boolean = false
    override fun doInBackground(vararg params: Void): Bitmap? {
        val conf = Bitmap.Config.ARGB_8888 // see other conf types
        var bmp = Bitmap.createBitmap(mWidth, mHeight, conf) // this creates a MUTABLE bitmap
        try {
            bmp = MvilLoadImageFetcher.decodeSampledBitmapFromUrl(strUrl, mWidth, mHeight)

        } catch (e: Exception) {
            error = true
            e.printStackTrace()
        }
        return bmp
    }

    override fun onPostExecute(bitmap: Bitmap) {
        imageView.visibility = View.VISIBLE
        if(!error) {
            if (mCacheImage) {
                mCache?.put(strUrl, bitmap)
            }
            MvilAnimation.fade(imageView, 1000)
            imageView.setImageBitmap(bitmap)
        }else{
            imageView.setImageBitmap(mPlaceHolderBitmap)
        }
    }
}