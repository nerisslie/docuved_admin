package com.siren.docuved

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.splashscreen)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }, 3000L)
    }
}