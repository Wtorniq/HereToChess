package com.example.heretochess.ui.vm

import androidx.lifecycle.ViewModel
import com.example.heretochess.model.firebase.FirebaseRepository

class StartViewModel(private val firebaseRepository: FirebaseRepository): ViewModel() {

    fun createLobby(lobbyName: String) {
        Thread{
            firebaseRepository.createLobby(lobbyName)
        }.start()
    }

    fun joinLobby(lobbyName: String) {
        firebaseRepository.joinLobby(lobbyName)
    }

    fun startGame() {
        firebaseRepository.completeLobby()
    }

}