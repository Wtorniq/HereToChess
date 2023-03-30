package com.example.heretochess.dagger

import com.example.heretochess.ui.vm.MainViewModel
import com.example.heretochess.ui.vm.StartViewModel
import dagger.Component

@Component(modules = [
    ChessBoardModule::class,
    CardsModule::class,
    MainModule::class,
    StartModule::class,
    FirebaseRepoModule::class])
interface AppComponent {
    fun getMainViewModel(): MainViewModel
    fun getStartViewModel(): StartViewModel
}