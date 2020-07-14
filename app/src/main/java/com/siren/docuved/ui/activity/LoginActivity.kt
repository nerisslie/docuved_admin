package com.siren.docuved.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.siren.docuved.R
import com.siren.docuved.base.BaseActivity

@SuppressLint("Registered")
class LoginActivity : BaseActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText

    private lateinit var txtForgotPassword: TextView

    private lateinit var btnLogin: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mEmail              = findViewById(R.id.email)
        mPassword           = findViewById(R.id.password)
        txtForgotPassword   = findViewById(R.id.txt_forgot_password)
        btnLogin            = findViewById(R.id.btn_login)
        btnHelp             = findViewById(R.id. btn_help)

        btnLogin.setOnClickListener {

            processLogin()
        }
    }

    private fun processLogin(){

        firebaseRef.collection("user")
            .whereEqualTo("email", mEmail.text.toString())
            .get()
            .addOnSuccessListener { result ->
                Log.d("result", result.toString())
                for (document in result) {
                    Log.d("test", "${document.id} => ${document.data}")
                }

                Intent(applicationContext, DashboardSiswa::class.java).run {
                    startActivity(this)
                }
            }
            .addOnFailureListener() { exception ->
                Log.w("err", "error getting documents.", exception)
            }
    }
}