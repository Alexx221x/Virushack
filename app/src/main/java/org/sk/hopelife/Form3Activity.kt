package org.sk.hopelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class Form3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form3)

        var relatives = findViewById<ListView>(R.id.rel)
        var dis = findViewById<ListView>(R.id.dis)
        var drugs = findViewById<ListView>(R.id.drugs)


    }
}
