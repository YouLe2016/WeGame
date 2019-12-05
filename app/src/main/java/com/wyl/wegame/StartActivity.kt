package com.wyl.wegame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        supportActionBar?.hide()


        // 全屏
        // google 设置方法
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or// 隐藏navigation bar
//                    View.SYSTEM_UI_FLAG_FULLSCREEN or// 全屏
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or // 应用的主体内容占用系统status bar的空间
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or // 沉浸式响应
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // 应用的主体内容占navigation bar的空间,同样占用status bar空间

        // RxFluxArchitecture 设置方法
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or// 应用的主体内容占用系统status bar的空间
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or// 应用的主体内容占用系统status bar的空间
//                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or // 应用的主体内容占navigation bar的空间,同样占用status bar空间
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or// 隐藏navigation bar
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY// 沉浸式响应


        // status bar颜色设置为透明
        window.statusBarColor = Color.TRANSPARENT
    }

}
