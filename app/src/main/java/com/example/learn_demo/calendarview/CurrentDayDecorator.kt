package com.example.learn_demo.calendarview

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.ForegroundColorSpan
import android.text.style.LineBackgroundSpan
import com.example.learn_demo.Utils
import com.example.learn_demo.Utils.dip2px
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import kotlin.math.min


/**
 * @author vianhuang
 * @date 2020/9/23 6:51 PM
 */
class CurrentDayDecorator(val context: Activity, currentDay: CalendarDay) : DayViewDecorator {
    var myDay = currentDay

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == myDay
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(AnnulusSpan())
    }

    inner class AnnulusSpan : LineBackgroundSpan {
        override fun drawBackground(
            c: Canvas,
            p: Paint,
            left: Int,
            right: Int,
            top: Int,
            baseline: Int,
            bottom: Int,
            text: CharSequence,
            start: Int,
            end: Int,
            lnum: Int
        ) {
            val paint = Paint()
            paint.isAntiAlias = true //消除锯齿
            paint.style = Paint.Style.STROKE //绘制空心圆或 空心矩形
            val ringWidth: Int = dip2px(context, 1F) //圆环宽度
            //绘制圆环
            paint.color = Color.parseColor("#1370F2")
            paint.strokeWidth = ringWidth.toFloat()
            val radius = min((right - left), (bottom - top)).toFloat()
            c.drawCircle(
                (right - left) / 2F,
                (bottom - top) / 2F,
                (right - left) / 2F - dip2px(context, 2F),
                paint
            )
        }
    }
}