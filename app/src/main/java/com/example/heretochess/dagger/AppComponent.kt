package com.example.heretochess.dagger

import com.example.heretochess.model.ChessModel
import com.example.heretochess.vm.MainViewModel
import dagger.Component

@Component(modules = [ChessBoardModule::class, MainModule::class])
interface AppComponent {
    fun getMainViewModel(): MainViewModel
}