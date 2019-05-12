package com.example.androidtp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_bottom_sheet.*


class BottomSheetActivity : AppCompatActivity() {

    lateinit var mBottomSheetBehavior: BottomSheetBehavior<View>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        supportActionBar!!.hide()

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        // Set it as collapsed initially
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)

        button_1.setOnClickListener {
            // Expand
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }

        button_2.setOnClickListener {
            // Collapse
            mBottomSheetBehavior.setPeekHeight(0)
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        button_3.setOnClickListener {
            // Peek
            mBottomSheetBehavior.setPeekHeight(300)
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)

        }

        // Make peeking gone when completely collapsed
        mBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                  //  mBottomSheetBehavior.setPeekHeight(0) // set o to completely GOne
                    mBottomSheetBehavior.setPeekHeight(100)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

    }


}
