package com.example.heretochess.model.cards

import com.example.heretochess.R
import com.example.heretochess.model.chess.ChessRank

class CardsModel {
    private val deck = arrayListOf<Card>()
    private val hand = arrayListOf<Card>()

    init {
        reset()
    }

    private fun reset(){
        for (i in 0..1){
            deck.add(Card(ChessRank.QUEEN, R.drawable.white_queen))
        }
        for (i in 0..3){
            deck.add(Card(ChessRank.ROOK, R.drawable.white_rook))
        }
        for (i in 0..8){
            deck.add(Card(ChessRank.PAWN, R.drawable.white_pawn))
        }
        makeHand()
    }
    private fun makeHand(){
        for (i in 0..4){
            val addedCard = deck.random()
            hand.add(addedCard)
            deck.remove(hand[i])
        }
    }
    fun getHand() = hand
}