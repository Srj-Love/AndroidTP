package com.example.androidtp.di

import javax.inject.Inject

/**
 * Inject constructor() :- will tell to dagger tat we may need the instance of this class in future.
 * this kind of injection called constructor Injection
  */


interface ApiService{
    fun  startService()
}