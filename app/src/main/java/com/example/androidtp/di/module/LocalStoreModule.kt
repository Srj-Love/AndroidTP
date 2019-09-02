package com.example.androidtp.di.module

import com.example.androidtp.di.LocalStore
import dagger.Module
import dagger.Provides

@Module // responsible for to provide instance of LogingStore
class LocalStoreModule {

    @Provides // : this funs will provide the instance to us from LocalStore
    fun provideLocalStore()=LocalStore()
}