package com.example.heretochess.model.cards

import com.example.heretochess.model.chess.ChessRank

data class Card(
    val rank: ChessRank,
    val resId: Int
)
