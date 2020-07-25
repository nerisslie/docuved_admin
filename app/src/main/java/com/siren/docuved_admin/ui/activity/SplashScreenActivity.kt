package com.siren.docuved_admin.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.siren.docuved_admin.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    companion object{

        const val REQUEST_CODE    = 555
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.splashscreen)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        setupPermissions()
    }

    private fun setupPermissions() {

        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED)
            makeRequest()
        else
            goToLogin()

    }

    private fun goToLogin(){

        GlobalScope.launch {

            delay(3000)
            Intent(applicationContext, LoginActivity::class.java).run{

                startActivity(this)
                finish()
            }
        }
    }

    private fun makeRequest() {

        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {

            REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    finish()
                else
                    goToLogin()
            }
        }
    }
}