package com.rodionov.oktan.presentation.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

class FuelTypeView(context: Context): @JvmOverloads View(context) {

    private val backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val foregroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val background = RectF()

    private var sweepAngle: Float = 0f
    private var strokeWidth: Float = 0f

    init {
        backgroundPaint.style = Paint.Style.STROKE
        foregroundPaint.style = Paint.Style.STROKE
        backgroundPaint.color = Color.BLUE
        foregroundPaint.color = Color.WHITE

        sweepAngle = 270f
        strokeWidth = 3f

        foregroundPaint.strokeWidth = strokeWidth
        foregroundPaint.strokeCap = Paint.Cap.ROUND
        backgroundPaint.strokeWidth = strokeWidth
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        background.set(
                0f + strokeWidth /2,
                0f + strokeWidth /2,
                width.toFloat() - strokeWidth /2,
                height.toFloat() - strokeWidth /2)

        canvas?.drawArc(background, 0f, 360f, false, backgroundPaint)
    }

}