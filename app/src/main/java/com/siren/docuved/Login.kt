package com.siren.docuved

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
class Login : AppCompatActivity(), View.OnClickListener {
    var email: EditText? = null
    var password: EditText? = null
    var lupa: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        email = findViewById<View>(R.id.email) as EditText
        password = findViewById<View>(R.id.password) as EditText
        val btn =
            findViewById<View>(R.id.button1) as Button
        btn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button1) {
            val i = Intent(this, DaftarSiswa::class.java)
            startActivity(i)
        }
    }
}