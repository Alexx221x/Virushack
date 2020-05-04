package org.sk.hopelife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TimersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timers)

        findViewById<TextView>(R.id.eat).setOnClickListener {
            val intent = Intent(this, SetDietActivity::class.java)
            startActivity(intent)
        }
    }
}
