package com.example.androidtp.di.module

import com.example.androidtp.di.AppLogger
import com.example.androidtp.di.scope.MyCustomAppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    private var index = 0

    @Provides
    @MyCustomAppScope
    fun provideAppLogger(): AppLogger {
        index++
        return AppLogger("App Logger: index: $index")
    }

}