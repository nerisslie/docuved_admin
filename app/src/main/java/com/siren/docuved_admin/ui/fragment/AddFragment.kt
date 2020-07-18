package com.siren.docuved_admin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.siren.docuved_admin.R
import android.widget.ArrayAdapter as ArrayAdapter

class AddFragment : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var spinnerJurusan: Spinner
    private lateinit var spinnerAdapter: SpinnerAdapter
    private lateinit var spinnerJurusanAdapter: SpinnerAdapter
    private val tahun = arrayOf(
        "Tahun Angkatan",
        "2017",
        "2018",
        "2019",
        "2020"
    )
    private val jurusan = arrayOf(
        "Jurusan",
        "IPA",
        "IPS"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add, container, false)
        spinner = root.findViewById<View>(R.id.tahun) as Spinner
        spinnerJurusan = root.findViewById<View>(R.id.jurusan) as Spinner
        spinner.adapter = spinnerAdapter
        spinnerJurusan.adapter = spinnerJurusanAdapter

        spinner.dropDownVerticalOffset
        spinnerJurusan.dropDownVerticalOffset

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        spinnerAdapter = ArrayAdapter(
            context,
            R.layout.spinner,
            tahun
        )
        spinnerJurusanAdapter = ArrayAdapter(
            context,
            R.layout.spinner,
            jurusan
        )
    }
}
