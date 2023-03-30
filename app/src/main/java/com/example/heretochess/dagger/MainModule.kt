package com.example.heretochess.dagger

import com.example.heretochess.model.cards.CardsModel
import com.example.heretochess.model.chess.ChessModel
import com.example.heretochess.ui.vm.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModel(chessModel: ChessModel, cardsModel: CardsModel): MainViewModel {
        return MainViewModel(chessModel, cardsModel)
    }
}