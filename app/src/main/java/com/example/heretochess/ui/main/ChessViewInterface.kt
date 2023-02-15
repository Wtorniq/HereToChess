package com.example.heretochess.ui.main

import com.example.heretochess.model.chess.ChessPiece

interface ChessViewInterface {
    fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int)
    fun onRemovePiece(row: Int, col: Int)
}