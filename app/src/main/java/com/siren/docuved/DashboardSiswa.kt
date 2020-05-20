package com.siren.docuved

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardSiswa : AppCompatActivity(), View.OnClickListener {
    var button: Button? = null
    protected fun OnCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_siswa)
        findViewById<View>(R.id.button1)
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button1) {
            val intent = Intent(this, DashboardSiswa::class.java)
            startActivity(intent)
        }
    }
}