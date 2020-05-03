package org.sk.hopelife

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object JSONHelper {

    fun loadUserToFireBase(user: User){
        val database = Firebase.database.reference
        database.child("users").child(user.email!!).setValue(user)
    }
}