package com.siren.docuved_admin.custom

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.lang.Exception

class Client(private val context: Context){

    fun isOnline(): Boolean {

        var isconnected = false

        val connMgr: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(connMgr != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                connMgr.getNetworkCapabilities(connMgr.activeNetwork).run {

                    if (this != null) {

                        when{

                            this.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)   -> isconnected  = true
                            this.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)       -> isconnected  = true
                            this.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)   -> isconnected  = true
                        }
                    }
                }
            } else {

                try {

                    connMgr.activeNetworkInfo.run {

                        if (this != null && this.isConnected)
                            isconnected = true
                    }
                } catch (e: Exception) {

                    isconnected = false
                }
            }
        }

        return isconnected
    }
}