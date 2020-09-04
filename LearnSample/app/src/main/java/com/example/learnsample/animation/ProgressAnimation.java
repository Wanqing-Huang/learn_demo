package com.example.learnsample.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;


/**
 * @author vianhuang
 * @date 2020/4/24 10:56 AM
 */
public class ProgressAnimation {
    public static void startAnim(final ProgressBar view, int startProgress, int endProgress, int duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startProgress, endProgress);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                view.setProgress(progress);
            }
        });
        valueAnimator.start();
    }
}
