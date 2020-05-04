package org.sk.hopelife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class COVIDTestAcrivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.covid_test_acrivity)

        findViewById<Button>(R.id.next).setOnClickListener {
            val intent = Intent(this, TestActivity::class.java).apply {
                putExtra("dis", "covid19")
            }
            startActivity(intent)
            this.finish()
        }
    }
}
