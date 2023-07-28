package com.example.diary.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.diary.data.Point
import java.util.ArrayList

class DrawView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val points = ArrayList<Point>()
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0 until points.size - 1) {
            val pointA = points[i]
            val pointB = points[i + 1]
            canvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                points.clear()
                points.add(Point(x, y))
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                points.add(Point(x, y))
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                points.add(Point(x, y))
                invalidate()
                return true
            }
        }
        return false
    }

    fun resetView() {
        points.clear()
        invalidate()
    }
}