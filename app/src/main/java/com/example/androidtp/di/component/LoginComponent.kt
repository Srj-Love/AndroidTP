package com.example.androidtp.di.component

import com.example.androidtp.activity.DaggerActivity
import com.example.androidtp.di.module.HttpServiceModule
import com.example.androidtp.di.module.LocalStoreModule
import dagger.Component

/**
 * // this interface give instance of the requested Files
 * @modules :- this interface gives modules from LocalStoreModule
 */
@Component(modules = [LocalStoreModule::class, HttpServiceModule::class])
interface LoginComponent {
    //    fun getLoginManager(): LoginManager
    // field injection
    fun inject(daggerActivity: DaggerActivity)
}