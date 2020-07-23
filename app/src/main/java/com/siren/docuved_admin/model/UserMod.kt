package com.siren.docuved_admin.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("admin_id") val admin_id: String,
    @SerializedName("username") val username    : String
)