package org.sk.hopelife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.reg).setOnClickListener {
            Log.i("fuck", "clicked")
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
