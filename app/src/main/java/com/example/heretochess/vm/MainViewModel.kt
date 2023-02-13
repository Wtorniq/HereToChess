package com.example.heretochess.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heretochess.App
import com.example.heretochess.model.ChessModel

class MainViewModel(private val model: ChessModel) : ViewModel() {

    private val liveData = MutableLiveData<ChessModel>()

    fun getLiveData() : LiveData<ChessModel> = liveData

    fun getBoard() = getBoardFromModel()

    private fun getBoardFromModel() {
        liveData.postValue(model.getChessBoard())
    }
}