package com.example.heretochess.model.firebase

data class PlayerDTO(
    val id: String,
    val name: String,
    val hand: ArrayList<String>,
    val piecesOnBoard: ArrayList<String>
)
