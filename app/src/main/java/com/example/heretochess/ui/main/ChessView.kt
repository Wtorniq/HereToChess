package com.example.heretochess.ui.main

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.lang.Math.abs

class ChessView(context: Context, attributeSet: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attributeSet) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        val paint = Paint()

        val margin = dpToPx(8)
        val squareSize = width / 8 - margin / 8

        val boardSize = squareSize * 8
        val boardWidth = boardSize + margin * 2

        val left = (width - boardWidth) / 2
        val top = (height - boardSize) / 3

        for (row in 0..7) {
            for (col in 0 ..7) {
                if ((row + col) % 2 == 0) {
                    paint.color = Color.LTGRAY
                } else {
                    paint.color = Color.DKGRAY
                }

                val rectLeft = left + margin + row * squareSize
                val rectTop = top + col * squareSize
                val rectRight = rectLeft + squareSize
                val rectBottom = rectTop + squareSize

                val rect = Rect(rectLeft, rectTop, rectRight, rectBottom)

                canvas!!.drawRect(rect, paint)
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        return kotlin.math.abs(dp * resources.displayMetrics.density).toInt()
    }

/*
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val paint = Paint()
        paint.color = Color.LTGRAY

        val width = w - dpToPx(16)
        val height = h - dpToPx(16)
        val squareSize = if (width < height) width / 8 else height / 8
        val margin = (width - 8 * squareSize) / 2
        val left = margin
        val top = (height - 8 * squareSize) / 2

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        for (i in 0 until 8) {
            for (j in 0 until 8) {
                if ((i + j) % 2 == 0) {
                    val rect = Rect(
                        left + i * squareSize,
                        top + j * squareSize,
                        left + (i + 1) * squareSize,
                        top + (j + 1) * squareSize
                    )
                    canvas.drawRect(rect, paint)
                }
            }
        }

        setImageBitmap(bitmap)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density + 0.5f).toInt()
    }*/
}