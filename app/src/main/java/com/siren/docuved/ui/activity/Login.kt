package com.siren.docuved.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.siren.docuved.R
import com.siren.docuved.base.BaseActivity

@SuppressLint("Registered")
class Login : BaseActivity() {
    var email: EditText? = null
    var password: EditText? = null
    var lupa: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
//        email = findViewById<View>(R.id.email) as EditText
//        password = findViewById<View>(R.id.password) as EditText
//        val btn = findViewById<View>(R.id.button1) as Button
//        btn.setOnClickListener(this)
    }

//    override fun onClick(v: View) {
//        if (v.id == R.id.button1) {
//            firebaseRef.collection("user")
//                .whereEqualTo("email", email)
//                .get()
//                .addOnSuccessListener { result ->
//                    Log.d("result", result.toString())
//                    for (document in result) {
//                        Log.d("test", "${document.id} => ${document.data}")
//                    }
//                }
//                .addOnFailureListener() { exception ->
//                    Log.w("err", "error getting documents.", exception)
//                }
//            val i = Intent(this, DashboardSiswa::class.java)
//            startActivity(i)
//        }
//    }
}