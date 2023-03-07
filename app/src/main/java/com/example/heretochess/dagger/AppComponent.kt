package com.example.heretochess.dagger

import com.example.heretochess.vm.MainViewModel
import dagger.Component

@Component(modules = [ChessBoardModule::class, CardsModule::class, MainModule::class])
interface AppComponent {
    fun getMainViewModel(): MainViewModel
}