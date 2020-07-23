package com.siren.docuved_admin.base

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Api
import com.google.firebase.firestore.FirebaseFirestore
import com.siren.docuved_admin.custom.Client
import com.siren.docuved_admin.custom.User
import dmax.dialog.SpotsDialog

abstract class BaseActivity: AppCompatActivity() {

    lateinit var client: Client
    lateinit var user: User
    lateinit var mProgressDialog: AlertDialog

    var firebaseRef = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        client              = Client(this)
        user                = User(this)

        mProgressDialog     = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Loading...")
            .setCancelable(true)
            .build()
    }

    fun showMessage(message: String){

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}