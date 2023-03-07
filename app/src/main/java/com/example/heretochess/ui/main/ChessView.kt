package com.example.heretochess.ui.main

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.heretochess.R
import com.example.heretochess.model.cards.Card
import com.example.heretochess.model.chess.ChessPiece

class ChessView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    var chessViewInterface : ChessViewInterface? = null
    private val displayMetrics = resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val boardPaint = Paint()
    private val consolePaint = Paint()
    private val margin = dpToPx(8)
    private var squareSize = 0
    private var boardSize = 0
    private var boardLeft = 0
    private var boardTop = 0
    private var board = arrayListOf(arrayListOf<ChessPiece?>())
    private var hand = arrayListOf<Card>()
    private var fromRow = -1
    private var fromCol = -1
    private var movingY = -1f
    private var movingX = -1f
    private var movingPiece: ChessPiece? = null
//    private val resIdSet = setOf(
//        R.drawable.white_king,
//        R.drawable.white_queen,
//        R.drawable.white_bishop,
//        R.drawable.white_knight,
//        R.drawable.white_pawn,
//        R.drawable.white_rook,
//        R.drawable.black_bishop,
//        R.drawable.black_king,
//        R.drawable.black_knight,
//        R.drawable.black_pawn,
//        R.drawable.black_queen,
//        R.drawable.black_rook
//    )
//    private val bitmaps = mutableMapOf<Int, Bitmap>()

//    init {
//        loadBitmaps()
//    }

    //    private fun loadBitmaps(){
//        resIdSet.forEach{
//            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
//        }
    fun setHand(addedHand: ArrayList<Card>) {
        hand = addedHand
    }

    fun setBoard(addedBoard: ArrayList<ArrayList<ChessPiece?>>){
        board = addedBoard
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeightSpec = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, desiredHeightSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        squareSize = width / 8
        boardSize = squareSize * 8

        boardLeft = (width - boardSize) / 2
        boardTop = (height - boardSize) / 4
        buildChessBoard(canvas)
        drawPieces(canvas)
//        drawConsole(canvas)
    }

    private fun drawConsole(canvas: Canvas) {

        val consoleSize = (height - (margin + boardTop + boardSize)).toFloat()
        val consoleLeft = boardLeft.toFloat()
        val consoleTop = (boardTop + boardSize + margin / 2).toFloat()
        val consoleRight = (boardLeft + boardSize).toFloat()
        val consoleBottom = (consoleTop + consoleSize)
        val cardSize = squareSize + squareSize / 5

        consolePaint.color = Color.LTGRAY
        canvas.drawRoundRect(consoleLeft, consoleTop, consoleRight, consoleBottom, 20f, 20f, consolePaint)

        consolePaint.color = Color.DKGRAY
        consolePaint.textSize = 50f
        canvas.drawText("00:00", (cardSize * 5.8).toFloat(), consoleTop + margin * 5, consolePaint)
        canvas.drawText("playerName", (consoleLeft + margin), consoleTop + margin * 5, consolePaint)

        for (i in 0..4){
            val cardLeft = consoleLeft + cardSize * i + margin
            val cardRight = cardLeft + cardSize
            val cardTop = consoleTop + consoleSize / 3 + margin
            val cardBottom = consoleBottom - margin
            val cardRect = RectF(cardLeft, cardTop, cardRight, cardBottom)
            canvas.drawBitmap(BitmapFactory.decodeResource(resources, hand[i].resId), null, cardRect, consolePaint)
        }
        
        val deckIconSize = consoleSize / 3
        canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.deck_icon),
            null,
            RectF(consoleLeft + cardSize * 5 + margin * 2, (consoleTop + consoleSize / 2), consoleRight - margin, (consoleTop + consoleSize / 2) + deckIconSize),
            consolePaint)
    }

/*    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        when (event.action){
            MotionEvent.ACTION_DOWN -> {
                fromCol = ((event.x - boardLeft) / squareSize).toInt()
                fromRow = ((event.y - boardTop) / squareSize).toInt()
                try {
                    movingPiece = model[7 - fromRow][fromCol]
                    chessViewInterface?.onRemovePiece(fromRow, fromCol)
                } catch (e: Throwable){return false}
            }
            MotionEvent.ACTION_MOVE -> {
                movingY = event.y
                movingX = event.x - squareSize / 2
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                val toCol = ((event.x - boardLeft) / squareSize).toInt()
                val toRow = ((event.y - boardTop) / squareSize).toInt()
                movingPiece ?: return false
                chessViewInterface?.onRegularMove(movingPiece!!, toRow, toCol)
                movingPiece = null
            }
        }
        return true
    }*/

    private fun drawPieces(canvas: Canvas){
        board.forEach { row ->
            row.forEach { piece ->
                piece?.let {
                    drawPieceAt(canvas, piece.col, piece.row, piece.resId)
                }
            }
        }
        setOnTouchListener { v, event ->
            v.performClick()
            event ?: return@setOnTouchListener false
            when (event.action){
                MotionEvent.ACTION_DOWN -> {
                    fromCol = ((event.x - boardLeft) / squareSize).toInt()
                    fromRow = ((event.y - boardTop) / squareSize).toInt()
                    try {
                        movingPiece = board[7 - fromRow][fromCol]
                        chessViewInterface?.onRemovePiece(fromRow, fromCol)
                    } catch (e: Throwable){
                        return@setOnTouchListener false
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    movingY = event.y
                    movingX = event.x - squareSize / 2
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    val toCol = ((event.x - boardLeft) / squareSize).toInt()
                    val toRow = ((event.y - boardTop) / squareSize).toInt()
                    movingPiece ?: return@setOnTouchListener false
                    chessViewInterface?.onRegularMove(movingPiece!!, toRow, toCol)
                    movingPiece = null
                }
            }
            return@setOnTouchListener true
        }
        movingPiece?.let {
            canvas.drawBitmap(
                BitmapFactory.decodeResource(resources, it.resId),
                null,
                RectF(movingX, movingY, movingX + squareSize, movingY + squareSize),
                boardPaint
            )
        }
    }

    private fun drawPieceAt(canvas: Canvas, row: Int, col: Int, pieceId: Int) {
        val rect = makeRect(row, col)
        val bitmap = BitmapFactory.decodeResource(resources, pieceId)
        canvas.drawBitmap(bitmap, null, rect, boardPaint)
    }

//    }
    private fun buildChessBoard(canvas: Canvas){
        for (row in 0..7) {
            for (col in 0 ..7) {
                if ((row + col) % 2 == 0) {
                    boardPaint.color = Color.LTGRAY
                } else {
                    boardPaint.color = Color.DKGRAY
                }
                val rect = makeRect(row, col)
                canvas.drawRect(rect, boardPaint)
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