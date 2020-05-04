package org.sk.hopelife

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object JSONHelper {

    val database = Firebase.database.reference

    fun loadUserToFireBase(user: User){
        database.child("users").child(user.email!!).setValue(user)
    }

    fun loadRecordToFireBase(record: Record, id:Int){

        val email = record.email!!.split("@")[0]

        database.child("records").child("${email}$id").setValue(record)
    }
}