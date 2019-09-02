package com.example.androidtp.di.component

import com.example.androidtp.di.AppLogger
import com.example.androidtp.di.module.AppModule
import com.example.androidtp.di.scope.MyCustomAppScope
import dagger.Component

@MyCustomAppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getAppLogger():AppLogger

}