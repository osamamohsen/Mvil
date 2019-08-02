package com.irozon.mvil.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.mind.valley.mvil.Mvil

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

        }
        else if (value is Int)
            Mvil.with(this.context).source(value).loadImage(this)
    }

var ImageView.placeholder: Drawable?
    get() = null
    set(source) {
        this.setImageDrawable(source)
    }
