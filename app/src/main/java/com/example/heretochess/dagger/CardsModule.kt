package com.example.heretochess.dagger

import com.example.heretochess.model.cards.CardsModel
import dagger.Module
import dagger.Provides

@Module
class CardsModule {

    @Provides
    fun providesCardsModel() = CardsModel()
}