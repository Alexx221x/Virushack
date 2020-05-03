package org.sk.hopelife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Form2Actvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form2)

        findViewById<Button>(R.id.next).setOnClickListener {
            var intent = Intent(this, Form3Activity::class.java)
            startActivity(intent)
        }
    }
}
