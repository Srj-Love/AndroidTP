package com.example.androidtp.di.scope

import javax.inject.Scope

/**
 * useful to instantiate data att first time only
 */
@Scope
@Repeatable
annotation class MyCustomAppScope