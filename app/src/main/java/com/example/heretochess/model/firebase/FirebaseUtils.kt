package com.example.heretochess.model.firebase

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

object FirebaseUtils {
    fun mainRef() = FirebaseDatabase.getInstance().reference
}