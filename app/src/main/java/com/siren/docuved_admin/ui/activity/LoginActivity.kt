package com.siren.docuved_admin.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.siren.docuved_admin.base.BaseActivity
import com.siren.docuved_admin.R
import com.siren.docuved_admin.model.UserResponse
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@SuppressLint("Registered")
class LoginActivity : BaseActivity() {

    private lateinit var mUsername: EditText
    private lateinit var mPassword: EditText

    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUsername           = findViewById(R.id.username)
        mPassword           = findViewById(R.id.password)
        btnLogin            = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {

            validateUser()
        }
    }

    public override fun onStart() {
        super.onStart()

        if(user.getUserData() != null)
            goToDashboard()
    }

    private fun validateUser(){

        when{
            isEmpty(mUsername)      ->  mUsername.error     = "Username is required"
            isEmpty(mPassword)      ->  mPassword.error     = "Password is required"
            client.isOnline()       ->  processLogin()
            else                    ->  showMessage("Tidak ada koneksi internet")
        }
    }

    private fun processLogin(){

        mProgressDialog.show()

        if(client.isOnline()) {

            firebaseRef.collection("admin")
                .whereEqualTo("username", mUsername.text.toString())
                .whereEqualTo("password", md5(mPassword.text.toString()))
                .limit(1)
                .get()
                .addOnSuccessListener { documents ->

                    if(documents != null && !documents.isEmpty){

                        for(document in documents) {

                            user.setUserData(
                                UserResponse(
                                    admin_id = document.id,
                                    username = document.data["username"].toString()
                                )
                            )
                        }

                        goToDashboard()
                    }else{

                        showMessage("Invalid Username or Password")
                    }
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }
                .addOnCompleteListener{

                    mProgressDialog.dismiss()
                }
        }else
            showMessage("No Internet Connection")
    }

    private fun goToDashboard(){

        showMessage("Success Login")

        Intent(applicationContext, DashboardActivity::class.java).run {
            startActivity(this)
            finish()
        }
    }

    private fun isEmpty(inputText: EditText)    = TextUtils.isEmpty(inputText.text.toString())

    private fun md5(s: String): String? {

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