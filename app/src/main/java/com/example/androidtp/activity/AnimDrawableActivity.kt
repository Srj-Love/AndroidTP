package com.example.androidtp.activity

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtp.R
import kotlinx.android.synthetic.main.activity_anim_drawable.*


class AnimDrawableActivity : AppCompatActivity() {

    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_drawable)

        supportActionBar!!.hide()

        // init constraintLayout
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = constraintLayout!!.background as AnimationDrawable

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000)

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000)

    }


    override fun onResume() {
        super.onResume()
        if (!animationDrawable!!.isRunning()) {
            // start the animation
            animationDrawable.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop()
        }
    }
}
