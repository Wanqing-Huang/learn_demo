package com.example.learn_demo.calendar

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.ForegroundColorSpan
import android.text.style.LineBackgroundSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


/**
 * @author vianhuang
 * @date 2020/9/23 6:51 PM
 */
class CurrentDayDecorator(context: Activity?, currentDay: CalendarDay) : DayViewDecorator {
    var myDay = currentDay

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == myDay
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(Color.parseColor("#fd755c")))
    }

//    class AnnulusSpan : LineBackgroundSpan {
//        override fun drawBackground(
//            c: Canvas,
//            p: Paint,
//            left: Int,
//            right: Int,
//            top: Int,
//            baseline: Int,
//            bottom: Int,
//            text: CharSequence,
//            start: Int,
//            end: Int,
//            lnum: Int
//        ) {
//            val paint = Paint()
//            paint.isAntiAlias = true //消除锯齿
//            paint.style = Paint.Style.STROKE //绘制空心圆或 空心矩形
//            val ringWidth: Int = dip2px(1) //圆环宽度
//            //绘制圆环
//            paint.color = Color.parseColor("#00bcbe")
//            paint.strokeWidth = ringWidth.toFloat()
//            c.drawCircle(
//                (right - left) / 2.toFloat(),
//                (bottom - top) / 2 + dip2px(4),
//                dip2px(18),
//                paint
//            )
//        }
//    }
}