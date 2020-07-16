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
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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

        Intent(applicationContext, DashboardSiswa::class.java).run {
            startActivity(this)
            finish()
        }

//        firebaseRef.collection("user")
//            .whereEqualTo("email", mEmail.text.toString())
//            .get()
//            .addOnSuccessListener { result ->
//                Log.d("result", result.toString())
//                for (document in result) {
//                    Log.d("test", "${document.id} => ${document.data}")
//                }
//
//            }
//            .addOnFailureListener() { exception ->
//                Log.w("err", "error getting documents.", exception)
//            }
    }

    fun md5(s: String): String? {
        try {
            // Create MD5 Hash
            val digest = MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                    0xFF and messageDigest[i].toInt()
                )
            )
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}