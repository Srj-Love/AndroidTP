package com.example.androidtp.di.module

import com.example.androidtp.di.ApiService
import com.example.androidtp.di.HttpService
import dagger.Binds
import dagger.Module

@Module // responsible for to provide instance of LogingStore
abstract class HttpServiceModule {

    //     @Provides // : this funs will provide the instance to us from LocalStore
    @Binds // : works same as Provider but needs abstract () and class. and the return will be take care by dagger
    abstract fun provideHttpService(httpService: HttpService): ApiService
}