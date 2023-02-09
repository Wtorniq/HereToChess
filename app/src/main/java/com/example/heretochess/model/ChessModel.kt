package com.example.heretochess.model

import dagger.Module
import dagger.Provides

class ChessModel {
    private val piecesBox = mutableSetOf<ChessPiece>()
    init {
        reset()
    }

    private fun reset(){
        piecesBox.removeAll(piecesBox)
        for (i in 0..1){
            piecesBox.add(ChessPiece(0 + (i * 7), 0, ChessPlayer.WHITE, ChessRank.ROOK))
            piecesBox.add(ChessPiece(0 + (i * 7), 7, ChessPlayer.BLACK, ChessRank.ROOK))

            piecesBox.add(ChessPiece(1 + (i * 5), 0, ChessPlayer.WHITE, ChessRank.KNIGHT))
            piecesBox.add(ChessPiece(1 + (i * 5), 7, ChessPlayer.BLACK, ChessRank.KNIGHT))

            piecesBox.add(ChessPiece(2 + (i * 3), 0, ChessPlayer.WHITE, ChessRank.BISHOP))
            piecesBox.add(ChessPiece(2 + (i * 3), 7, ChessPlayer.BLACK, ChessRank.BISHOP))
        }
        for (i in 0..7){
            piecesBox.add(ChessPiece(i, 1, ChessPlayer.WHITE, ChessRank.PAWN))
            piecesBox.add(ChessPiece(i, 6, ChessPlayer.BLACK, ChessRank.PAWN))
        }
        piecesBox.add(ChessPiece(3, 0, ChessPlayer.WHITE, ChessRank.QUEEN))
        piecesBox.add(ChessPiece(3, 7, ChessPlayer.BLACK, ChessRank.QUEEN))

        piecesBox.add(ChessPiece(4, 0, ChessPlayer.WHITE, ChessRank.KING))
        piecesBox.add(ChessPiece(4, 7, ChessPlayer.BLACK, ChessRank.KING))
    }

    private fun pieceAt(row: Int, col: Int): ChessPiece? {
        for (piece in piecesBox){
            if (piece.row == row && piece.col == col) return piece
        }
        return null
    }

    fun getChessBoard(): String{
        var desc = " \n"
        for (row in 7 downTo 0){
            desc += "$row"
            for (col in 0..7){
                val piece = pieceAt(row, col)
                desc += if (piece == null){
                    "  ."
                } else {
                    val isWhite = piece.player == ChessPlayer.WHITE
                    when (piece.rank){
                        ChessRank.KING -> { if(isWhite) " k" else " K" }
                        ChessRank.QUEEN ->  { if(isWhite) " q" else " Q" }
                        ChessRank.ROOK ->  { if(isWhite) " r" else " R" }
                        ChessRank.KNIGHT ->  { if(isWhite) " n" else " N" }
                        ChessRank.BISHOP ->  { if(isWhite) " b" else " B" }
                        ChessRank.PAWN ->  { if(isWhite) " p" else " P" }
                    }
                }
            }
            desc += "\n"
        }
        desc += "   0 1 2 3 4 5 6 7"
        return desc
    }
}