package com.example.androidtp

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.example.androidtp.activity.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar!!.hide()

        btn_anim_drawable.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    AnimDrawableActivity::class.java
                )
            )
        }

        btn_bottom_sheet.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    BottomSheetActivity::class.java
                )
            )
        }

        btn_custom_tab.setOnClickListener {
            openCustomTabs()
        }

        // extract color from bitmap
        btn_extract_color.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ExtractColorActivity::class.java
                )
            )
        }

        // Access Google Drive
        btn_google_drive.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GoogleDriveActivity::class.java
                )
            )
        }
        btn_linkage_rcv.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LinkageRCV::class.java
                )
            )
        }


        mStartAnimBtn()


    }

    private fun openCustomTabs() {
        Snackbar.make(btn_custom_tab, "Launch Custom Chrome Tab ?", Snackbar.LENGTH_LONG)
            .setAction("Yes") {
                loadChromeCustomTab(this@MainActivity, "https://movieskida.com/")
            }
            .show()

    }

    private fun loadChromeCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)

        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        val intentBuilder = CustomTabsIntent.Builder()

        // set toolbar color
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        intentBuilder.setSecondaryToolbarColor(
            ContextCompat.getColor(context, R.color.colorPrimaryDark)
        )
        intentBuilder.setShowTitle(true)

        // set start and exit animations
        intentBuilder.setStartAnimations(
            context, android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        intentBuilder.setExitAnimations(
            context, android.R.anim.slide_out_right,
            android.R.anim.slide_in_left
        )

        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        // NOTE : If you do not have Chrome installed, the intent will launch the default
        // browser installed on the device. The CustomTabsIntent simply launches an
        // implicit intent (android.intent.action.VIEW) and passes an extra data in the
        // intent (i.e. android.support.customtabs.extra.SESSION and
        // android.support.customtabs.extra.TOOLBAR_COLOR) that gets ignored if the
        // default browser cannot process this information.
        val customTabsIntent = intentBuilder.build()

        // add share action to menu list
        intentBuilder.addDefaultShareMenuItem()

        // Setup a icon in the menu bar
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, url)
        val requestCode = 100
        val pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        // Map the bitmap, text, and pending intent to this icon
        // Set tint to be true so it matches the toolbar color
        intentBuilder.setActionButton(bitmap, "Share Link", pendingIntent, true)

        // Set close button icon
        val bitmapBack = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
        intentBuilder.setCloseButtonIcon(bitmapBack)

        // and launch the desired Url with CustomTabsIntent.launchUrl()
        customTabsIntent.launchUrl(context, uri)

    }

    private fun mStartAnimBtn() {
        // init constraintLayout
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.drawable_gradient_animation_list
        ) as AnimationDrawable

        btn_anim_drawable.background = animationDrawable

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000)

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000)
    }


    override fun onResume() {
        super.onResume()
        if (!animationDrawable.isRunning) {
            // start the animation
            animationDrawable.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (animationDrawable.isRunning) {
            // stop the animation
            animationDrawable.stop()
        }
    }


}
