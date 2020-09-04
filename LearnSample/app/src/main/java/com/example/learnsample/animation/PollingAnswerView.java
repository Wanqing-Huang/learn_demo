package com.example.learnsample.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.learnsample.R;


/**
 * @author vianhuang
 * @date 2020/4/24 2:18 PM
 */
public class PollingAnswerView extends FrameLayout {

    private static final int ANIMATION_DURATION = 300;
    private static final float ANSWER_OPTION_SCALE = 0.7f;
    private final int GREEN = getResources().getColor(R.color.feeds_polling_sticker_green);
    private final int ORINGE = getResources().getColor(R.color.feeds_polling_sticker_oringe);
    private final int GRAY = getResources().getColor(R.color.feeds_polling_sticker_gray_txt);
    private final int BLACK = getResources().getColor(R.color.feeds_polling_sticker_black_txt);
    private final int pollingViewWidth = getResources().getDimensionPixelSize(R.dimen.feeds_polling_width);
    private final int answerHorizontalPadding = getResources().getDimensionPixelSize(R.dimen.feeds_polling_answer_horizontal_padding) * 2;

    private ValueAnimator progressAnimator;

    private ProgressBar progressView;
    private RelativeLayout leftAnswerView;
    private RelativeLayout rightAnswerView;
    private TextView leftTxtView;
    private TextView rightTxtView;
    private View leftRatioView;
    private View rightRatioView;
    private TextView leftRatioTxtView;
    private TextView rightRatioTxtView;
    private TextView leftPercentView;
    private TextView rightPercentView;
    private View dividerView;

    private View.OnClickListener leftAnswerClickListener;
    private View.OnClickListener rightAnswerClickListener;



    public enum HighlightType {
        LEFT, RIGHT, BOTH
    }

    public PollingAnswerView(Context context) {
        super(context);
        init();
    }

    public PollingAnswerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PollingAnswerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.polling_ratio_view, null);
        addView(view);

        progressView = ((ViewGroup) view).findViewById(R.id.progress_view);
        leftAnswerView = ((ViewGroup) view).findViewById(R.id.left_answer);
        rightAnswerView = ((ViewGroup) view).findViewById(R.id.right_answer);
        leftTxtView = ((ViewGroup) view).findViewById(R.id.left_answer_txt);
        rightTxtView = ((ViewGroup) view).findViewById(R.id.right_answer_txt);
        leftRatioView = ((ViewGroup) view).findViewById(R.id.left_answer_ratio);
        rightRatioView = ((ViewGroup) view).findViewById(R.id.right_answer_ratio);
        leftRatioTxtView = ((ViewGroup) view).findViewById(R.id.left_ratio_txt);
        rightRatioTxtView = ((ViewGroup) view).findViewById(R.id.right_ratio_txt);
        leftPercentView = ((ViewGroup) view).findViewById(R.id.left_percent);
        rightPercentView = ((ViewGroup) view).findViewById(R.id.right_percent);
        dividerView = ((ViewGroup) view).findViewById(R.id.polling_view_divider);
    }

    public void initClickListener() {
        leftAnswerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftAnswerClickListener != null) {
                    leftAnswerClickListener.onClick(v);
                }
            }
        });
        rightAnswerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightAnswerClickListener != null) {
                    rightAnswerClickListener.onClick(v);
                }
            }
        });
    }

    public void setLeftAnswerClickListener(OnClickListener leftAnswerClickListener) {
        this.leftAnswerClickListener = leftAnswerClickListener;
    }

    public void setRightAnswerClickListener(OnClickListener rightAnswerClickListener) {
        this.rightAnswerClickListener = rightAnswerClickListener;
    }

    /**
     * 显示投票选项
     *
     * @param leftAnswer  左选项文字
     * @param rightAnswer 右选项文字
     */
    public void showPolling(String leftAnswer, String rightAnswer) {
        if (progressAnimator != null && progressAnimator.isRunning()) {
            progressAnimator.cancel();
        }

        updateAnswerWidth(0.5f);
        updateText(leftAnswer, rightAnswer, -1);
        updateTextSize(0);
        updateTextColor(HighlightType.LEFT, 0, 0);
        resetProgressBar(HighlightType.LEFT, 0);
        dividerView.setVisibility(View.VISIBLE);
    }

    /**
     * 展示投票结果
     *
     * @param highlightType 高亮类型
     * @param leftRatio     左边选项占的比例
     * @param leftAnswer    左选项文字
     * @param rightAnswer   右选项文字
     */
    public void showPollingResult(HighlightType highlightType, float leftRatio, String leftAnswer, String rightAnswer) {
        if (progressAnimator != null && progressAnimator.isRunning()) {
            progressAnimator.cancel();
        }

        updateText(leftAnswer, rightAnswer, leftRatio);
        updateTextSize(1);
        updateTextColor(highlightType, 1, leftRatio);

        float progressRatio = adjustProgressRatio(leftRatio);
        resetProgressBar(highlightType, (int) (progressRatio * 100));

        float answerRatio = adjustAnswerRatio(leftRatio);
        updateAnswerWidth(answerRatio);

        dividerView.setVisibility(View.INVISIBLE);
    }

    /**
     * 展示投票动画
     *
     * @param highlightType 高亮类型
     * @param leftRatio     左边选项占的比例
     * @param leftAnswer    左选项文字
     * @param rightAnswer   右选项文字
     */
    public void startPollingAnim(HighlightType highlightType, float leftRatio, String leftAnswer, String rightAnswer) {
        if (progressAnimator != null && progressAnimator.isRunning()) {
            progressAnimator.cancel();
        }

        resetProgressBar(highlightType, 0);
        updateText(leftAnswer, rightAnswer, leftRatio);
        dividerView.setVisibility(View.INVISIBLE);

        //进度条动画
        float startRatio = 0;
        if (highlightType == HighlightType.RIGHT) {
            startRatio = 1;
        }
        startProgressAnimation(highlightType, startRatio, leftRatio);
    }

    /**
     * 投票进度条动画
     */
    private void startProgressAnimation(final HighlightType highlightType, final float startRatio, final float endRatio) {
        final float progressEndRatio = adjustProgressRatio(endRatio);
        final float answerEndRatio = adjustAnswerRatio(endRatio);

        progressAnimator = ValueAnimator.ofFloat(0, 1);
        progressAnimator.setDuration(ANIMATION_DURATION);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                progressView.setProgress((int) (((progressEndRatio - startRatio) * value + startRatio) * 100));
                updateAnswerWidth((answerEndRatio - 0.5f) * value + 0.5f);
                updateTextSize(value);
                updateTextColor(highlightType, value, endRatio);
            }
        });
        progressAnimator.start();
    }

    private void resetProgressBar(HighlightType highlightType, int progress) {
        switch (highlightType) {
            case LEFT:
                progressView.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal_green));
                break;
            case RIGHT:
                progressView.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal_oringe));
                break;
            case BOTH:
                progressView.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal_green_and_oringe));
                break;
        }
        progressView.setProgress(progress);
    }

    /**
     * 更新投票文案
     */
    private void updateText(String leftAnswer, String rightAnswer, float leftRatio) {
        if (leftRatio >= 0 && leftRatio <= 1) {
            int leftRatioValue = (int) (leftRatio * 100);
            int rightRatioValue = 100 - leftRatioValue;
            leftRatioView.setVisibility(View.VISIBLE);
            rightRatioView.setVisibility(View.VISIBLE);
            leftRatioTxtView.setText(String.valueOf(leftRatioValue));
            rightRatioTxtView.setText(String.valueOf(rightRatioValue));
        } else {
            leftRatioView.setVisibility(View.GONE);
            rightRatioView.setVisibility(View.GONE);
        }

        leftTxtView.setText(leftAnswer);
        rightTxtView.setText(rightAnswer);

        //根据文字单双行，改变文字大小
        if (leftTxtView.getMeasuredWidth() == 0) {
            leftTxtView.measure(0, 0);
        }
        if (rightTxtView.getMeasuredWidth() == 0) {
            rightTxtView.measure(0, 0);
        }
        if (leftTxtView.getLineCount() > 1 || rightTxtView.getLineCount() > 1) {
            leftTxtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            rightTxtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        }
    }

    /**
     * 更新投票文案字体大小
     */
    private void updateTextSize(float ratio) {
        //左选项文字
        scale(leftTxtView, 1 - (1 - ANSWER_OPTION_SCALE) * ratio);
        //右选项文字
        scale(rightTxtView, 1 - (1 - ANSWER_OPTION_SCALE) * ratio);
        //左比例文字
        scale(leftRatioView, ratio);
        //右比例文字
        scale(rightRatioView, ratio);
    }

    /**
     * 更新投票文案颜色
     */
    private void updateTextColor(HighlightType highlightType, float ratio, float targetLeftRatio) {
        int leftTxtColor = 0;
        int rightTxtColor = 0;
        switch (highlightType) {
            case LEFT:
                leftTxtColor = blendColors(GREEN, BLACK, ratio);
                rightTxtColor = blendColors(ORINGE, GRAY, ratio);
                break;
            case RIGHT:
                leftTxtColor = blendColors(GREEN, GRAY, ratio);
                if (targetLeftRatio > 0.73f) {
                    rightTxtColor = blendColors(ORINGE, BLACK, ratio);
                } else {
                    rightTxtColor = blendColors(ORINGE, Color.WHITE, ratio);
                }
                break;
            case BOTH:
                leftTxtColor = blendColors(GREEN, BLACK, ratio);
                if (targetLeftRatio > 0.73f) {
                    rightTxtColor = blendColors(ORINGE, BLACK, ratio);
                } else {
                    rightTxtColor = blendColors(ORINGE, Color.WHITE, ratio);
                }
                break;
        }

        leftTxtView.setTextColor(leftTxtColor);
        leftRatioTxtView.setTextColor(leftTxtColor);
        leftPercentView.setTextColor(leftTxtColor);

        rightTxtView.setTextColor(rightTxtColor);
        rightRatioTxtView.setTextColor(rightTxtColor);
        rightPercentView.setTextColor(rightTxtColor);
    }

    /**
     * 更新投票选项占比
     */
    private void updateAnswerWidth(float leftRatio) {
        float translation = (leftRatio - 0.5f) / 2 * pollingViewWidth;
        if (leftRatio == 0) {
            leftAnswerView.setVisibility(View.INVISIBLE);
        } else {
            leftAnswerView.setVisibility(View.VISIBLE);
            leftAnswerView.setTranslationX(translation);
        }

        if (leftRatio == 1) {
            rightAnswerView.setVisibility(View.INVISIBLE);
        } else {
            rightAnswerView.setVisibility(View.VISIBLE);
            rightAnswerView.setTranslationX(translation);
        }
    }

    /**
     * 颜色渐变
     *
     * @param color1 起始颜色
     * @param color2 终止颜色
     * @param ratio  颜色变化频率 从0-1
     * @return 颜色值
     */
    private int blendColors(int color1, int color2, float ratio) {
        float inverseRatio = 1.0F - ratio;
        float a = (float) Color.alpha(color1) * inverseRatio + (float) Color.alpha(color2) * ratio;
        float r = (float) Color.red(color1) * inverseRatio + (float) Color.red(color2) * ratio;
        float g = (float) Color.green(color1) * inverseRatio + (float) Color.green(color2) * ratio;
        float b = (float) Color.blue(color1) * inverseRatio + (float) Color.blue(color2) * ratio;
        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }

    /**
     * 调整进度条显示比例
     */
    private float adjustProgressRatio(float ratio) {
        if (ratio > 0 && ratio < 0.05) {
            return 0.05f;
        }
        if (ratio > 0.95 && ratio < 1) {
            return 0.95f;
        }
        return ratio;
    }

    /**
     * 调整选项宽度显示比例
     */
    private float adjustAnswerRatio(float ratio) {
        float leftTxtWidth = leftTxtView.getPaint().measureText(String.valueOf(leftTxtView.getText()));
        leftTxtWidth = Math.min(leftTxtWidth, pollingViewWidth / 2f);
        float rightTxtWidth = rightTxtView.getPaint().measureText(String.valueOf(rightTxtView.getText()));
        rightTxtWidth = Math.min(rightTxtWidth, pollingViewWidth / 2f);

        float leftMinRatio = (leftTxtWidth * ANSWER_OPTION_SCALE + answerHorizontalPadding * 2) / pollingViewWidth;
        float rightMinRatio = (rightTxtWidth * ANSWER_OPTION_SCALE + answerHorizontalPadding * 2) / pollingViewWidth;
        if (ratio > 0 && ratio < leftMinRatio) {
            return leftMinRatio;
        } else if (ratio > 1 - rightMinRatio && ratio < 1) {
            return 1 - rightMinRatio;
        } else {
            return ratio;
        }
    }

    private void scale(View view, float ratio) {
        view.setScaleX(ratio);
        view.setScaleY(ratio);
    }
}
