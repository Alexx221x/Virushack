package org.sk.hopelife

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<TextView>(R.id.login).setOnClickListener(this)
        findViewById<Button>(R.id.next).setOnClickListener(this)
    }

    /**
     * TODO: добавить логику firebase
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login -> {
                val intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
                this.finish()
            }
            R.id.next -> {
                if (checkAll()){
                    val auth = Firebase.auth
                    val email = findViewById<TextInputEditText>(R.id.email).text.toString()
                    val password = findViewById<TextInputEditText>(R.id.password).text.toString()
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                            } else {
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    val intent = Intent(this, Form2Actvity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun checkAll(): Boolean{
        val f = findViewById<TextInputEditText>(R.id.email).text.isNullOrEmpty()
        val s = findViewById<TextInputEditText>(R.id.password).text.isNullOrEmpty()
        val t = findViewById<CheckBox>(R.id.check).isChecked

        if (f or s or !t){
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}
