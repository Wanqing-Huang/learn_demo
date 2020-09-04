package com.example.learnsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.learnsample.animation.AnimationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.animation_btn).setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AnimationActivity::class.java)
            startActivity(intent)
        })
    }
}
