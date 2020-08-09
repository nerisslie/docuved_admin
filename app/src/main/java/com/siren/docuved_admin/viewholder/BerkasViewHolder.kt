package com.siren.docuved_admin.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siren.docuved_admin.R
import com.siren.docuved_admin.model.BerkasResponse

class BerkasViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.detail_berkas, parent, false)){

    private var txtName: TextView = itemView.findViewById(R.id.txt_name)

    @ExperimentalStdlibApi
    fun bind(berkasResponse: BerkasResponse) {

        txtName.text       = berkasResponse.name
    }
}