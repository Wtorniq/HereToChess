package com.example.heretochess.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heretochess.model.cards.Card
import com.example.heretochess.model.cards.CardsModel
import com.example.heretochess.model.chess.ChessModel
import com.example.heretochess.model.chess.ChessPiece

class MainViewModel(private val chessModel: ChessModel, private val cardsModel: CardsModel) : ViewModel() {

    private val boardLiveData = MutableLiveData<ArrayList<ArrayList<ChessPiece?>>>()
    private val handLiveData = MutableLiveData<ArrayList<Card>>()

    fun getBoardLiveData() : LiveData<ArrayList<ArrayList<ChessPiece?>>> = boardLiveData
    fun getHandLiveData() = handLiveData

    fun getBoard() = getBoardFromModel()

    fun getHand() = getHandFromModel()

    private fun getHandFromModel(){
        handLiveData.postValue(cardsModel.getHand())
    }

    private fun getBoardFromModel() {
        boardLiveData.postValue(chessModel.getChessBoard())
    }

    fun regularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        chessModel.regularMove(movingPiece, toRow, toCol)
        boardLiveData.postValue(chessModel.getChessBoard())
    }

    fun removePiece(row: Int, col: Int) {
        chessModel.removePiece(row, col)
        boardLiveData.postValue(chessModel.getChessBoard())
    }
}