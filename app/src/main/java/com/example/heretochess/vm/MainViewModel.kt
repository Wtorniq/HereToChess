package com.example.heretochess.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heretochess.model.chess.ChessModel
import com.example.heretochess.model.chess.ChessPiece

class MainViewModel(private val model: ChessModel) : ViewModel() {

    private val liveData = MutableLiveData<ArrayList<ArrayList<ChessPiece?>>>()

    fun getLiveData() : LiveData<ArrayList<ArrayList<ChessPiece?>>> = liveData

    fun getBoard() = getBoardFromModel()

    private fun getBoardFromModel() {
        liveData.postValue(model.getChessBoard())
    }

    fun regularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        model.regularMove(movingPiece, toRow, toCol)
        liveData.postValue(model.getChessBoard())
    }

    fun removePiece(row: Int, col: Int) {
        model.removePiece(row, col)
        liveData.postValue(model.getChessBoard())
    }
}