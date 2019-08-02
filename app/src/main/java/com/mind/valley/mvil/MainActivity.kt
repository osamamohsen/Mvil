package com.mind.valley.mvil

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.irozon.mvil.extension.source
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var first = "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128&s=622a88097cf6661f84cd8942d851d9a2"
    var second = "https://images.unsplash.com/profile-1441738874514-bf742aedca3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128&s=5ee215da5f36d49fb6432ed1c51bdced"
    var third = "https://images.unsplash.com/profile-1464519397431-f860aa18227f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128&s=9563c375f482f24a7024f18c3d0eb14f"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Mvil.with(this)
            .placeholder(R.drawable.ic_sad)
            .source(first)
            .loadImage(img_first)

        Mvil.with(this)
            .setMaxCapacityCache(10f)
            .placeholder(R.drawable.ic_sad)
            .resize(300, 300) // here resize image default (500 x 500)
            .disableCache() // Disable cache. By default its enabled -> enableCache(true)
            .source(second)
            .loadImage(img_second)

        img_third.source = third
    }
}
