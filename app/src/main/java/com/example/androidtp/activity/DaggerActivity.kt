package com.example.androidtp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtp.R
import com.example.androidtp.di.LoginManager
import com.example.androidtp.di.component.DaggerLoginComponent
import javax.inject.Inject

class DaggerActivity : AppCompatActivity() {

    //    private var loginManager: LoginManager? = null
    @Inject // field injection
    lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        // generate Dagger login Components
        val loginComponent = DaggerLoginComponent.create()
        loginComponent.inject(this)
//        loginManager = loginComponent.getLoginManager()
        loginManager.login("", "")


    }

}
