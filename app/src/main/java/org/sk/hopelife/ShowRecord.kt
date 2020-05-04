package org.sk.hopelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShowRecord : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_record)

        val date = intent.extras?.getString("date")
        val temp = intent.extras?.getString("temp")
        val pres = intent.extras?.getString("pres")
        val desc = intent.extras?.getString("desc")

        findViewById<TextView>(R.id.date).text = date
        findViewById<TextView>(R.id.temp).text = temp
        findViewById<TextView>(R.id.pres).text = pres
        findViewById<TextView>(R.id.desc).text = desc

        findViewById<Button>(R.id.prev).setOnClickListener {
            this.finish()
            onBackPressed()
        }

    }
}
