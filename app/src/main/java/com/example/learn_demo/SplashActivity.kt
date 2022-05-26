package com.example.learn_demo

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.learn_demo.calendarview.CalendarViewActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        title = "SplashActivity"
        setContentView(R.layout.activity_splash)

        //控制splashScreen显示隐藏
        splashScreen.setKeepOnScreenCondition { true }
        lifecycleScope.launch(Dispatchers.Main) {
            delay(500)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

        //定义splashScreen退出动画
//        splashScreen.setOnExitAnimationListener { splashScreenView ->
//            // Create your custom animation.
//            val slideUp = ObjectAnimator.ofFloat(
//                splashScreenView.view,
//                View.TRANSLATION_Y,
//                0f,
//                -splashScreenView.view.height.toFloat()
//            )
//            slideUp.interpolator = AnticipateInterpolator()
//            slideUp.duration = 200L
//
//            // Call SplashScreenView.remove at the end of your custom animation.
//            slideUp.doOnEnd { splashScreenView.remove() }
//
//            // Run your animation.
//            slideUp.start()
//        }

//        app_version.setOnClickListener {
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//        }
    }
}