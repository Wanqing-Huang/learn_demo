package com.example.learnsample.animation;

/**
 * @author vianhuang
 * @date 2020/4/23 2:59 PM
 */

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learnsample.R;

public class AnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        final View shake = findViewById(R.id.shake);
        shake.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ShakeAnimation.tada(shake);
                animator.start();
            }
        });

        final PollingAnswerView pollingRatioView = findViewById(R.id.polling_view);
        final int oringe = Color.parseColor("#EE4D2D");
        final int green = Color.parseColor("#50E3C2");
        pollingRatioView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                pollingRatioView.startPollingAnim(PollingAnswerView.HighlightType.RIGHT, 0.1f, "qwert", "qwertyuiopasdfgh");
            }
        });
//        pollingRatioView.showPollingResult(PollingAnswerView.HighlightType.RIGHT, 0.9f, "abcdef", "qwerty");
        pollingRatioView.showPolling("qwertyuiopasdqwertyuiopasdk", "qwertyuiopasdqwertyuiopasdjj");

//        LottieAnimationView animationView = findViewById(R.id.animation_view);
//        animationView.playAnimation();

        RelativeLayout container = findViewById(R.id.container_view);
        TextView txtView = findViewById(R.id.txt_view);
        LinearLayout leftView = findViewById(R.id.left);

        txtView.setScaleX(0.5f);
        txtView.setScaleY(0.5f);
        leftView.setTranslationX(100);

    }
}

