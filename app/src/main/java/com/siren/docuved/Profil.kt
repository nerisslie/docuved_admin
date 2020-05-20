package com.siren.docuved

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.util.*

class Profil : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil)
        val spinner = findViewById<View>(R.id.tahun) as Spinner
        val tahun = arrayOf(
            "Tahun Angkatan",
            "2017",
            "2018",
            "2019",
            "2020"
        )
        val tahunList: List<String> =
            ArrayList(Arrays.asList(*tahun))
        val spinnerArrayAdapter: ArrayAdapter<String> =
            object : ArrayAdapter<String?>(this, R.layout.spinner, tahunList) {
                override fun isEnabled(position: Int): Boolean {
                    return if (position == 0) {
                        false
                    } else {
                        true
                    }
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View,
                    parent: ViewGroup
                ): View {
                    val view =
                        super.getDropDownView(position, convertView, parent)
                    val textView = view as TextView
                    if (position == 0) {
                        textView.setTextColor(Color.GRAY)
                    } else {
                        textView.setTextColor(Color.BLACK)
                    }
                    return view
                }
            }
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner)
        spinner.adapter = spinnerArrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItemText =
                    parent.getItemAtPosition(position) as String
                if (position > 0) {
                    Toast.makeText(
                        applicationContext,
                        "Selected : $selectedItemText",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil)
        val spinner2 = findViewById<View>(R.id.jurusan) as Spinner
        val jurusan = arrayOf(
            "Jurusan",
            "Akuntansi",
            "Multimedia",
            "TKJ",
            "RPL"
        )
        val jurusanList: List<String> =
            ArrayList(Arrays.asList(*jurusan))
        val spinnerArrayAdapter: ArrayAdapter<String> =
            object : ArrayAdapter<String?>(this, R.layout.spinner, jurusanList) {
                override fun isEnabled(position: Int): Boolean {
                    return if (position == 0) {
                        false
                    } else {
                        true
                    }
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View,
                    parent: ViewGroup
                ): View {
                    val view =
                        super.getDropDownView(position, convertView, parent)
                    val textView = view as TextView
                    if (position == 0) {
                        textView.setTextColor(Color.GRAY)
                    } else {
                        textView.setTextColor(Color.BLACK)
                    }
                    return view
                }
            }
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner)
        spinner2.adapter = spinnerArrayAdapter
        spinner2.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItemText =
                    parent.getItemAtPosition(position) as String
                if (position > 0) {
                    Toast.makeText(
                        applicationContext,
                        "Selected : $selectedItemText",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.siswa -> {
                if (checked) break
                if (checked) break
            }
            R.id.alumni -> if (checked) break
        }
    }
}