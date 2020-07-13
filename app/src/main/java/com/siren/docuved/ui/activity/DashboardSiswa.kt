package com.siren.docuved.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.siren.docuved.R
import com.siren.docuved.base.BaseActivity

class DashboardSiswa : BaseActivity(), View.OnClickListener {
    var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_siswa)
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