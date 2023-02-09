package com.example.heretochess.model

import dagger.Module
import dagger.Provides

class ChessModel {
    fun getChessBoard(): String{
        var desc = " \n"
        for (row in 0..7){
            desc += "${7 - row}"
            for (col in 0..7){
                desc += "  ."
            }
            desc += "\n"
        }
        desc += "   0 1 2 3 4 5 6 7"
        return desc
    }
}