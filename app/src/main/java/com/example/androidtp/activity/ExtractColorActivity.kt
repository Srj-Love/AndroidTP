package com.example.androidtp.activity

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.example.androidtp.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_extract_color.*


class ExtractColorActivity : AppCompatActivity() {


    var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extract_color)


        progressDialog = ProgressDialog(this);
        progressDialog?.setIndeterminate(true);
        progressDialog?.setCancelable(true);
        progressDialog?.setMessage("Fetching from server..");

        loadRandomImage();

        btn_reload.setOnClickListener({ loadRandomImage() })

    }

    private fun loadRandomImage() {
        Picasso.with(this@ExtractColorActivity).isLoggingEnabled = true


        Picasso.with(this@ExtractColorActivity)
            .load("https://source.unsplash.com/user/erondu/250x250")
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    main_image.setImageBitmap(bitmap)
                    Palette.from(bitmap).generate(object : Palette.PaletteAsyncListener {
                        override fun onGenerated(palette: Palette?) {
                            val darkMutedSwatch = palette?.getDarkMutedSwatch()

                            if (darkMutedSwatch == null) {
                                Toast.makeText(this@ExtractColorActivity, "Null swatch :(", Toast.LENGTH_SHORT).show()
                                return
                            }
                            main_background.setBackgroundColor(darkMutedSwatch.getRgb())
                            main_title_text.setTextColor(darkMutedSwatch.getTitleTextColor())
                            main_body_text.setTextColor(darkMutedSwatch.getBodyTextColor())
                            progressDialog?.cancel()
                        }

                    })
                }

                override fun onBitmapFailed(errorDrawable: Drawable) {
                    progressDialog?.cancel()
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {
                    progressDialog?.show()
                }
            })
    }


}
