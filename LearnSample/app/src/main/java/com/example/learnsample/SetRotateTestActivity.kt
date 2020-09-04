package com.example.learnsample

import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * @author vianhuang
 * @date 2020/4/10 11:24 AM
 */
class SetRotateTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)

        val view = findViewById<View>(R.id.rotate_view)
        val rotateBtn = findViewById<Button>(R.id.rotate_btn)
        val scaleBtn = findViewById<Button>(R.id.scale_btn)
        val translateBtn = findViewById<Button>(R.id.translate_btn)
        val resetBtn = findViewById<Button>(R.id.reset_btn)
        val printBtn = findViewById<Button>(R.id.print_btn)
        val container = findViewById<FrameLayout>(R.id.container)

        rotateBtn.setOnClickListener {
            view.rotation = 45f
        }
        scaleBtn.setOnClickListener {
            view.scaleX = 2f
            view.scaleY = 2f
        }
        translateBtn.setOnClickListener {
            view.translationX = 50f
            view.translationY = 50f
        }

        resetBtn.setOnClickListener {
            view.apply {
                rotation = 0f
                scaleX = 1f
                scaleY = 1f
                translationX = 0f
                translationY = 0f
            }
        }

        printBtn.setOnClickListener({
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            val left = location[0]
            val top = location[1]
            val right = left + view.width * view.scaleX
            val bottom = top + view.height * view.scaleY


            Log.d("vianhuang", "viewLocation:[$left,$top-$right,$bottom]" +
                    ", pivotX = ${view.pivotX}, pivotY = ${view.pivotY}" +
                    ", translationX = ${view.translationX}, translationY = ${view.translationY}" +
                    ", scaleX = ${view.scaleX},  scaleY = ${view.scaleY}" +
                    ", rotation = ${view.rotation}")
            var point = Point(40,30)
            var newPoint = getNewPoint(point, 30f)
            Log.d("vianhuang", "point = ${point.x}, ${point.y}, newPoint = ${newPoint.x}, ${newPoint.y}")
        })

        container.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if(event.action == MotionEvent.ACTION_DOWN){
                    var point = Point(event.rawX.toInt(), event.rawY.toInt())
                    val downPoint = getNewPoint(point, view.rotation)

                    val location = IntArray(2)
                    view.getLocationOnScreen(location)
                    point = Point(location[0], location[1])

                    val rectPoint = getNewPoint(point, view.rotation)
                    val right = rectPoint.x + view.width * view.scaleX
                    val bottom = rectPoint.y + view.height * view.scaleY
                    val rect = Rect(rectPoint.x, rectPoint.y, right.toInt(), bottom.toInt())
                    if(isInside(rect, downPoint)){
                        Log.d("vianhuang", "inside")
                        return true
                    }
                }
                return false
            }
        }
        )
    }

    private fun isInside(rect: Rect, point: Point): Boolean{
        Log.d("vianhuang", "rect.left=${rect.left}, rect.right=${rect.right}, rect.top=${rect.top}, rect.bottom=${rect.bottom}")
        return point.y >= rect.top && point.y <= rect.bottom && point.x >= rect.left && point.x <= rect.right
    }

    private fun getNewPoint(point: Point, angle: Float): Point{
        val radian =   Math.toRadians(angle.toDouble())
        val cos = Math.cos(radian)
        val sin = Math.sin(radian)
        val x = point.x * cos + point.y * sin
        val y = point.y * cos - point.x * sin
        Log.d("vianhuang", "getNewPoint = $x, $y, point.x=${point.x}, point.y=${point.y}, angle = $angle, radian = $radian, cos = $cos, sin = $sin" )

        val newPoint = Point()
        newPoint.x = Math.round(x).toInt()
        newPoint.y = Math.round(y).toInt()
        return newPoint
    }
}