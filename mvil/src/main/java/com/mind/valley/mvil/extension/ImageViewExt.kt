package com.irozon.mvil.extension

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.mind.valley.mvil.Mvil
import com.mind.valley.mvil.R

/**
 * Created by Osama Mohsen
 */


var ImageView.source: Any
    get() = ""
    set(value) {
        if (value is String) {
            Mvil.with(this.context)
                .source(value)
                .loadImage(this)

        } else if (value is Int)
            Mvil.with(this.context).source(value).loadImage(this)
    }

//var ImageView.placeholder: Int?
//    get() = R.drawable.abc_ic_ab_back_material
//    set(imgSource) {
//        this.setImageDrawable(imgSource?.let { ContextCompat.getDrawable(this.context, it) })
//    }
