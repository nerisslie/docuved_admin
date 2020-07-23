package com.siren.docuved_admin.custom

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.siren.docuved_admin.model.UserResponse

class User(context: Context){

    private val gson: Gson = Gson()
    private var mSettings: SharedPreferences?   = null

    init {

        mSettings = context.getSharedPreferences("Session", Context.MODE_PRIVATE)
    }

    companion object{

        const val SESSION_KEY   = "admUser"
    }

    fun getUserData(): UserResponse?    = gson.fromJson(mSettings?.getString(SESSION_KEY, null), UserResponse::class.java)

    fun setUserData(userResponse: UserResponse){

        mSettings?.edit().also {

            it?.putString(SESSION_KEY, gson.toJson(userResponse))
            it?.apply()
        }
    }

    fun removeUserData(){

        mSettings?.edit()?.clear()?.apply()
    }
}