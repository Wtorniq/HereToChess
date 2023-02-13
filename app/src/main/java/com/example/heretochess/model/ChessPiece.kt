package com.example.heretochess.model

data class ChessPiece(
    val col: Int,
    val row: Int,
    val player: ChessPlayer,
    val rank: ChessRank,
    val resId: Int
)
