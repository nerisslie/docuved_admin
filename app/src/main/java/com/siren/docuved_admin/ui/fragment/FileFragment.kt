package com.siren.docuved_admin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.siren.docuved_admin.R


class FileFragment : Fragment() {

    private lateinit var btnUpload: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root    = inflater.inflate(R.layout.fragment_file, container, false)

        btnUpload   = root.findViewById(R.id.btn_upload)

        btnUpload.setOnClickListener {

            chooseImage()
        }

        return root
    }

    private fun chooseImage(){

        Intent(Intent.ACTION_GET_CONTENT).run {

            this.type = "application/pdf"
            this.addCategory(Intent.CATEGORY_OPENABLE)
            activity?.startActivityForResult(

                Intent.createChooser(this, "Select a File to Upload"), 0
            )
        }
    }
}