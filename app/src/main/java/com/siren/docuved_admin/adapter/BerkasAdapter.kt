package com.siren.docuved_admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siren.docuved_admin.model.BerkasResponse
import com.siren.docuved_admin.viewholder.BerkasViewHolder

class BerkasAdapter(
    private val berkasList: List<BerkasResponse>):  RecyclerView.Adapter<BerkasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BerkasViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return BerkasViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = berkasList.size

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: BerkasViewHolder, position: Int) {

        holder.bind(berkasList[position])
    }
}