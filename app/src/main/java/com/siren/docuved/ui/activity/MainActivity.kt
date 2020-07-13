package com.siren.docuved.ui.activity

import android.os.Bundle
import android.util.Log
import com.siren.docuved.R
import com.siren.docuved.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseRef.collection("user")
            .whereEqualTo("name", "")
            .get()
            .addOnSuccessListener { result ->
                Log.d("result", result.toString())
                for (document in result) {
                    Log.d("test", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("err", "Error getting documents.", exception)
            }
    }
}
