package com.example.heretochess.ui.main

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.heretochess.R

class ChessView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint()
    private val margin = dpToPx(8)
    private var squareSize = 0
    private var boardSize = 0
    private var boardLeft = 0
    private var boardTop = 0
    private val resIdSet = setOf(
        R.drawable.white_king,
        R.drawable.white_queen,
        R.drawable.white_bishop,
        R.drawable.white_knight,
        R.drawable.white_pawn,
        R.drawable.white_rook,
        R.drawable.black_bishop,
        R.drawable.black_king,
        R.drawable.black_knight,
        R.drawable.black_pawn,
        R.drawable.black_queen,
        R.drawable.black_rook
    )
    private val bitmaps = mutableMapOf<Int, Bitmap>()

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        squareSize = width / 8 - margin / 8
        boardSize = squareSize * 8

        boardLeft = (width - boardSize) / 2
        boardTop = (height - boardSize) / 3
        buildChessBoard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas?){
        drawPieceAt(canvas, 0, 0, R.drawable.black_queen)
        drawPieceAt(canvas, 7, 7, R.drawable.white_queen)
    }

    private fun drawPieceAt(canvas: Canvas?, row: Int, col: Int, pieceId: Int) {
        val rect = makeRect(row, col)
        val bitmap = bitmaps[pieceId]!!
        canvas?.drawBitmap(bitmap, null, rect, paint)
    }

    private fun loadBitmaps(){
        resIdSet.forEach{
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun buildChessBoard(canvas: Canvas?){

        for (row in 0..7) {
            for (col in 0 ..7) {
                if ((row + col) % 2 == 0) {
                    paint.color = Color.LTGRAY
                } else {
                    paint.color = Color.DKGRAY
                }
                val rect = makeRect(row, col)
                canvas!!.drawRect(rect, paint)
            }
        }
    }
    private fun makeRect(row: Int, col: Int): Rect {
        val rectLeft = boardLeft + row * squareSize
        val rectTop = boardTop + col * squareSize
        val rectRight = rectLeft + squareSize
        val rectBottom = rectTop + squareSize

        return Rect(rectLeft, rectTop, rectRight, rectBottom)
    }
    private fun dpToPx(dp: Int): Int {
        return kotlin.math.abs(dp * resources.displayMetrics.density).toInt()
    }

}