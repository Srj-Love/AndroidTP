package com.example.androidtp.di

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.example.androidtp.di.component.DaggerAppComponent
import com.example.androidtp.di.module.AppModule

class MyApp : MultiDexApplication() {

    companion object {
        const val TAG = "MyApp"
    }

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()

        Log.d(TAG, appComponent.getAppLogger().values)
        Log.v(TAG, appComponent.getAppLogger().values)
        Log.i(TAG, appComponent.getAppLogger().values)
    }
}