package com.example.androidtp.di

import javax.inject.Inject

public class LoginManager @Inject constructor(val localStore: LocalStore, val apiService: ApiService) {

    public fun login(usenrName: String, pawd: String) {

    }

    @Inject // method Injection
    fun enableCache(cache: Cache){

        cache.getCacheData(this)
    }
}