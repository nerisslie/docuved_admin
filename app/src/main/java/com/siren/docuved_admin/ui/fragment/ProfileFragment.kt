package com.siren.docuved_admin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.siren.docuved_admin.R
import com.siren.docuved_admin.ui.activity.DashboardActivity

class ProfileFragment : Fragment() {

    private lateinit var btnLogOut: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        btnLogOut   = root.findViewById(R.id.btn_log_out)

        btnLogOut.setOnClickListener {

            (activity as DashboardActivity).logout()
        }

        return root
    }
}