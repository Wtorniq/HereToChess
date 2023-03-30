package com.example.heretochess.dagger

import com.example.heretochess.model.firebase.FirebaseRepository
import dagger.Module
import dagger.Provides

@Module
class FirebaseRepoModule {

    @Provides
    fun provideFirebaseRepo() = FirebaseRepository()
}