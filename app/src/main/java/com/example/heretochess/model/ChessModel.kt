package com.example.heretochess.model

import com.example.heretochess.R
import dagger.Module
import dagger.Provides

class ChessModel {
    private val piecesBox = mutableSetOf<ChessPiece>()
    val chessModel = arrayListOf(arrayListOf<ChessPiece?>())
    init {
        reset()
        movePiece(0, 0, 2, 5)
    }

    private fun reset(){
        piecesBox.removeAll(piecesBox)
        for (i in 0..1){
            piecesBox.add(ChessPiece(0 + (i * 7), 7, ChessPlayer.WHITE, ChessRank.ROOK, R.drawable.white_rook))
            piecesBox.add(ChessPiece(0 + (i * 7), 0, ChessPlayer.BLACK, ChessRank.ROOK, R.drawable.black_rook))

            piecesBox.add(ChessPiece(1 + (i * 5), 7, ChessPlayer.WHITE, ChessRank.KNIGHT, R.drawable.white_knight))
            piecesBox.add(ChessPiece(1 + (i * 5), 0, ChessPlayer.BLACK, ChessRank.KNIGHT, R.drawable.black_knight))

            piecesBox.add(ChessPiece(2 + (i * 3), 7, ChessPlayer.WHITE, ChessRank.BISHOP, R.drawable.white_bishop))
            piecesBox.add(ChessPiece(2 + (i * 3), 0, ChessPlayer.BLACK, ChessRank.BISHOP, R.drawable.black_bishop))
        }
        for (i in 0..7){
            piecesBox.add(ChessPiece(i, 6, ChessPlayer.WHITE, ChessRank.PAWN, R.drawable.white_pawn))
            piecesBox.add(ChessPiece(i, 1, ChessPlayer.BLACK, ChessRank.PAWN, R.drawable.black_pawn))
        }
        piecesBox.add(ChessPiece(3, 7, ChessPlayer.WHITE, ChessRank.QUEEN, R.drawable.white_queen))
        piecesBox.add(ChessPiece(3, 0, ChessPlayer.BLACK, ChessRank.QUEEN, R.drawable.black_queen))

        piecesBox.add(ChessPiece(4, 7, ChessPlayer.WHITE, ChessRank.KING, R.drawable.white_king))
        piecesBox.add(ChessPiece(4, 0, ChessPlayer.BLACK, ChessRank.KING, R.drawable.black_king))
    }

    private fun pieceAt(row: Int, col: Int): ChessPiece? {
        for (piece in piecesBox){
            if (piece.row == row && piece.col == col) return piece
        }
        return null
    }

    private fun movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int){
        val movingPiece = pieceAt(fromRow, fromCol) ?: return

        pieceAt(toRow, toCol)?.let {
            if (it.player == movingPiece.player) return
            piecesBox.remove(it)
        }
        piecesBox.remove(movingPiece)
        piecesBox.add(ChessPiece(toCol, toRow, movingPiece.player, movingPiece.rank, movingPiece.resId))
    }

    fun getChessBoard(): ArrayList<ArrayList<ChessPiece?>>{
        chessModel.clear()
        for (row in 7 downTo 0){
            val rowArrayList = arrayListOf<ChessPiece?>()
            for (col in 0..7){
                val piece = pieceAt(row, col)
                if (piece == null){
                    rowArrayList.add(null)
                } else {
                    rowArrayList.add(piece)
                }
            }
            chessModel.add(rowArrayList)
        }
        return chessModel
    }

}