package com.example.heretochess.dagger

import com.example.heretochess.model.chess.ChessModel
import dagger.Module
import dagger.Provides

@Module
class ChessBoardModule {

    @Provides
    fun providesChessBoardModel() = ChessModel()
}