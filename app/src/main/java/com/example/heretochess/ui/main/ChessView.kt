package com.example.heretochess.ui.main

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.lang.Math.abs

class ChessView(context: Context, attributeSet: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attributeSet) {

    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
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

                val rect = makeRect(rectLeft,rectTop,rectRight,rectBottom)

                canvas!!.drawRect(rect, paint)
            }
        }
    }

    private fun makeRect(rectLeft: Int, rectTop: Int, rectRight: Int, rectBottom: Int) = Rect(rectLeft, rectTop, rectRight, rectBottom)
    private fun dpToPx(dp: Int): Int {
        return kotlin.math.abs(dp * resources.displayMetrics.density).toInt()
    }

}