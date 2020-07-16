package com.siren.docuved.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.siren.docuved.R
import java.util.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }
}
//        val spinner = findViewById<View>(R.id.tahun) as Spinner
//        val tahun = arrayOf(
//            "Tahun Angkatan",
//            "2017",
//            "2018",
//            "2019",
//            "2020"
//        )
//        val tahunList: List<String> =
//            ArrayList(Arrays.asList(*tahun))
//        val spinnerArrayAdapter: SpinnerAdapter =
//            object : ArrayAdapter<String?>(this,
//                R.layout.spinner, tahunList) {
//                override fun isEnabled(position: Int): Boolean {
//                    return position != 0
//                }
//
//                override fun getDropDownView(
//                    position: Int,
//                    convertView: View,
//                    parent: ViewGroup
//                ): View {
//                    val view =
//                        super.getDropDownView(position, convertView, parent)
//                    val textView = view as TextView
//                    if (position == 0) {
//                        textView.setTextColor(Color.GRAY)
//                    } else {
//                        textView.setTextColor(Color.BLACK)
//                    }
//                    return view
//                }
//            }
////        spinnerArrayAdapter.getDropDownView(R.layout.spinner)
//
//        spinner.adapter = spinnerArrayAdapter
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                val selectedItemText =
//                    parent.getItemAtPosition(position) as String
//                if (position > 0) {
//                    Toast.makeText(
//                        this,
//                        "Selected : $selectedItemText",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {}

//        val spinner2 = findViewById<View>(R.id.jurusan) as Spinner
//        val jurusan = arrayOf(
//            "Jurusan",
//            "Akuntansi",
//            "Multimedia",
//            "TKJ",
//            "RPL"
//        )
//        val jurusanList: List<String> =
//            ArrayList(Arrays.asList(*jurusan))
//        val spinnerArrayAdapter2: ArrayAdapter<String?> =
//            object : ArrayAdapter<String?>(this, R.layout.spinner, jurusanList) {
//                override fun isEnabled(position: Int): Boolean {
//                    return if (position == 0) {
//                        false
//                    } else {
//                        true
//                    }
//                }
//
//                override fun getDropDownView(
//                    position: Int,
//                    convertView: View,
//                    parent: ViewGroup
//                ): View {
//                    val view =
//                        super.getDropDownView(position, convertView, parent)
//                    val textView = view as TextView
//                    if (position == 0) {
//                        textView.setTextColor(Color.GRAY)
//                    } else {
//                        textView.setTextColor(Color.BLACK)
//                    }
//                    return view
//                }
//            }
//        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner)
//        spinner2.adapter = spinnerArrayAdapter2
//        spinner2.onItemSelectedListener = object : OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                val selectedItemText =
//                    parent.getItemAtPosition(position) as String
//                if (position > 0) {
//                    Toast.makeText(
//                        applicationContext,
//                        "Selected : $selectedItemText",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }

//    }
//
//    fun onRadioButtonClicked(view: View) {
//        val checked = (view as RadioButton).isChecked
//        when (view.getId()) {
////            R.id.siswa -> {
////                if (checked) break
////                if (checked) break
////            }
////            R.id.alumni -> if (checked) break
//        }
//    }
//}