package org.sk.hopelife

class User(
    val name: String?,
    val surname: String?,
    val midName: String?,
    val city: String?,
    val age: Int?,
    val weight: Int?,
    val phone: String?,
    val email: String?,
    val relativies: ArrayList<ArrayList<String>>?,
    val disies: ArrayList<String>?,
    val drugs: ArrayList<ArrayList<String>>?,
    val doctor: MutableMap<String, String>?
)