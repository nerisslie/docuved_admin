package com.siren.docuved

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DaftarSiswa : AppCompatActivity(), View.OnClickListener {
    protected fun OnCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftarsiswa)
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button1) {
            val intent = Intent(this, DashboardAdmin::class.java)
            startActivity(intent)
        }
    }
}