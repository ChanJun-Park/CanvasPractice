package com.jingom.canvaspractice

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CanvasCustomView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

	private val paint = Paint().apply {
		this.color = Color.BLACK
		this.style = Paint.Style.FILL
	}

	private val linePaint = Paint().apply {
		this.color = Color.RED
		this.strokeWidth = 3f
		this.style = Paint.Style.STROKE
	}

	private val textPaint = Paint().apply {
		this.color = Color.RED
		this.strokeWidth = 3f
		this.style = Paint.Style.STROKE
		this.textSize = 10f
	}

	private val rectPaint = Paint().apply {
		this.color = Color.WHITE
		this.strokeWidth = 3f
		this.style = Paint.Style.STROKE
	}

	private val colors = intArrayOf(0xFFFFFF00.toInt(), 0xFFFFFF00.toInt())

	private val circlePaint = Paint()

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)

		circlePaint.shader = RadialGradient(
			(w / 2).toFloat(),
			(h / 2).toFloat(),
			50.dpToPx(),
			Color.YELLOW,
			Color.RED,
			Shader.TileMode.CLAMP
		)
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)

		canvas.apply {
			drawRect(
				0f,
				0f,
				width.toFloat(),
				height.toFloat(),
				paint
			)

			for (i in 1..20) {
				drawText(
					"${(25 * i)}px",
					(25 * i).dpToPx(),
					0f,
					textPaint
				)

				drawLine(
					(25 * i).dpToPx(),
					0f,
					(25 * i).dpToPx(),
					height.toFloat(),
					linePaint
				)

				drawText(
					"${(25 * i)}px",
					0f,
					(25 * i).dpToPx(),
					textPaint
				)

				drawLine(
					0f,
					(25 * i).dpToPx(),
					width.toFloat(),
					(25 * i).dpToPx(),
					linePaint
				)
			}

			drawRect(
				25.dpToPx(), 25.dpToPx(), 225.dpToPx(), 125.dpToPx(),
				rectPaint
			)

			drawCircle(width.toFloat() / 2, height.toFloat() / 2, 50.dpToPx(), circlePaint)

		}
	}

	private fun Int.dpToPx() = this * Resources.getSystem().displayMetrics.density
}