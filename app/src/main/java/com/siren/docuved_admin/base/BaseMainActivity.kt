package com.siren.docuved_admin.base

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.siren.docuved_admin.custom.Client
import com.siren.docuved_admin.custom.User
import com.siren.docuved_admin.ui.activity.LoginActivity
import dmax.dialog.SpotsDialog

abstract class BaseMainActivity: AppCompatActivity() {

    lateinit var client: Client
    lateinit var user: User
    lateinit var mProgressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        client              = Client(this)
        user                = User(this)

        mProgressDialog     = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Loading...")
            .setCancelable(true)
            .build()

        checkUserSession()
    }

    private fun checkUserSession(){

        if(user.getUserData() == null) {

            showMessage("Logged Out")
            logout()
        }
    }

    fun showMessage(message: String){

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun logout(){

        user.removeUserData()

        showMessage("Logged Out")

        Intent(this, LoginActivity::class.java).run {

            flags   = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }
}