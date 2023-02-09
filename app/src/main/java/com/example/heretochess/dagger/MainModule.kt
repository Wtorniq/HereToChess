package com.example.heretochess.dagger

import com.example.heretochess.model.ChessModel
import com.example.heretochess.vm.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModel(chessModel: ChessModel): MainViewModel{
        return MainViewModel(chessModel)
    }
}