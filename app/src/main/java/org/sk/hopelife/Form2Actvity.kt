package org.sk.hopelife

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText


class Form2Actvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form2)

        findViewById<Button>(R.id.next).setOnClickListener {
            if (checkAll()){
                val info = arrayListOf(
                    findViewById<TextInputEditText>(R.id.name).text.toString(),
                    findViewById<TextInputEditText>(R.id.surname).text.toString(),
                    findViewById<TextInputEditText>(R.id.midname).text.toString(),
                    findViewById<TextInputEditText>(R.id.city).text.toString(),
                    findViewById<TextInputEditText>(R.id.age).text.toString(),
                    findViewById<TextInputEditText>(R.id.weight).text.toString(),
                    findViewById<TextInputEditText>(R.id.phone).text.toString(),
                    findViewById<TextInputEditText>(R.id.email).text.toString()
                )
                var intent = Intent(this, Form3Activity::class.java).apply {
                    putExtra("info", info)
                }
                startActivity(intent)
            }
        }
    }

    fun checkAll(): Boolean{
        val f = findViewById<TextInputEditText>(R.id.name).text.isNullOrEmpty()
        val s = findViewById<TextInputEditText>(R.id.surname).text.isNullOrEmpty()
        val t = findViewById<TextInputEditText>(R.id.midname).text.isNullOrEmpty()
        val fo = findViewById<TextInputEditText>(R.id.city).text.isNullOrEmpty()
        val fi = findViewById<TextInputEditText>(R.id.age).text.isNullOrEmpty()
        val si = findViewById<TextInputEditText>(R.id.weight).text.isNullOrEmpty()
        val se = findViewById<TextInputEditText>(R.id.phone).text.isNullOrEmpty()
        val e = findViewById<TextInputEditText>(R.id.email).text.isNullOrEmpty()

        if (f or s or t or fo or fi or si or se or e){
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}
