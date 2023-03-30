package com.example.heretochess.dagger

import com.example.heretochess.model.firebase.FirebaseRepository
import com.example.heretochess.ui.vm.StartViewModel
import dagger.Module
import dagger.Provides

@Module
class StartModule {
    @Provides
    fun provideStartViewModel(firebaseRepository: FirebaseRepository) = StartViewModel(firebaseRepository)
}