package com.siren.docuved_admin.model

import com.google.gson.annotations.SerializedName

data class BerkasResponse(

    @SerializedName("file") val file: String? = null,
    @SerializedName("keterangan") val keterangan: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("nomor_berkas") val nomor_berkas    : String? = null,
    @SerializedName("type") val type    : String? = null
)