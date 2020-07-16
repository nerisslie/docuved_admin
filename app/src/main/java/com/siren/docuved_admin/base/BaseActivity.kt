package com.siren.docuved.base

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseActivity: AppCompatActivity() {

    var firebaseRef = FirebaseFirestore.getInstance()
}