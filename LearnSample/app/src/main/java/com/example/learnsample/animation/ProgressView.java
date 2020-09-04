package com.example.learnsample.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * @author vianhuang
 * @date 2020/4/24 2:18 PM
 */
public class ProgressView extends View {
    private int radius = 0;
    private int circleRadius = 0;
    private float progress = 0;
    private int backgroundColor = Color.WHITE;
    private int color = Color.GREEN;
    private Orientation orientation;

    private Paint paint;
    private RectF rect;


    public ProgressView(Context context) {
        super(context);
        init(null, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        if (orientation == Orientation.LEFT) {
            setRotation(0);
        } else if (orientation == Orientation.RIGHT) {
            setRotation(180);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);

        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.ALL_SAVE_FLAG);

        if(circleRadius > 0){
            drawLeftRect(canvas);
            drawInnerCircle(canvas, getWidth() / 4, getHeight() / 2);
            drawRightRect(canvas);
        }else if(progress > 0){
            drawWholeRect(canvas);
            drawInnerRect(canvas, 0, (int) (progress * getWidth()));
        }else{
            drawWholeRect(canvas);
        }

        canvas.restoreToCount(sc);
    }

    private void drawLeftRect(Canvas canvas) {
        paint.setXfermode(null);
        paint.setColor(backgroundColor);
        canvas.drawRect(radius, 0, getWidth() / 2, getHeight(), paint);
        rect = new RectF(0, 0, getWidth() / 2, getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

    private void drawRightRect(Canvas canvas) {
        paint.setXfermode(null);
        paint.setColor(backgroundColor);
        canvas.drawRect(getWidth() / 2, 0, getWidth() - radius, getHeight(), paint);
        rect = new RectF(getWidth() / 2, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

    private void drawWholeRect(Canvas canvas) {
        paint.setXfermode(null);
        paint.setColor(backgroundColor);
        rect = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

    private void drawInnerCircle(Canvas canvas, float cx, float cy) {
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(color);
        canvas.drawCircle(cx, cy, circleRadius, paint);
    }

    private void drawInnerRect(Canvas canvas, int left, int right) {
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(color);
        canvas.drawRect(left, 0, right, getHeight(), paint);
    }

    public enum Orientation {
        LEFT, RIGHT
    }
}
