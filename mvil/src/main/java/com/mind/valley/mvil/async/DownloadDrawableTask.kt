package com.mind.valley.mvil.async

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.irozon.mvil.cache.ImageCache
import com.mind.valley.mvil.manager.MvilAnimation
import com.mind.valley.mvil.manager.MvilLoadImageFetcher


/*
    private val imageView: ImageView,
    private val mCache: ImageCache?,
    private val mCacheImage: Boolean,
    private val mWidth: Int,
    private val mHeight: Int,
    private val strUrl: String,
    private val mPlaceHolderBitmap: Bitmap?
 */
class DownloadDrawableTask(
    private val mContext: Context?,
    private val imageView: ImageView,
    private val mCache: ImageCache?,
    private val mCacheImage: Boolean,
    private val mWidth: Int,
    private val mHeight: Int,
    var res: Int
) : AsyncTask<Void, Void, Bitmap>() {
    var error: Boolean = false
    override fun doInBackground(vararg params: Void): Bitmap? {
        val conf = Bitmap.Config.ARGB_8888 // see other conf types
        var bmp = Bitmap.createBitmap(mWidth, mHeight, conf) // this creates a MUTABLE bitmap
        try {
//          Decode Bitmap
            bmp = mContext?.let {
                MvilLoadImageFetcher.decodeSampledBitmapFromDrawable(
                    it,
                    res,
                    mWidth,
                    mHeight
                )
            }
        } catch (e: Exception) {
            error = true
            e.printStackTrace()
        }
        return bmp
    }

    override fun onPostExecute(bitmap: Bitmap) {
        imageView.visibility = View.VISIBLE
        if (mCacheImage) {
            bitmap.let { it1 -> mCache?.put(res.toString(), it1) }
        }
        MvilAnimation.fade(imageView, 1000)
        imageView.setImageBitmap(bitmap)
    }
}