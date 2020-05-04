package org.sk.hopelife

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val sharedPref: SharedPreferences = getSharedPreferences("state", 0)
        val show: Boolean = sharedPref.getBoolean("show", false)

        if (show){
            findViewById<Button>(R.id.care).visibility = View.VISIBLE
        }

        findViewById<Button>(R.id.diary).setOnClickListener(this)
        findViewById<Button>(R.id.timers).setOnClickListener(this)
        findViewById<Button>(R.id.covid_info).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var intent: Intent? = null
        when(v?.id){
            R.id.covid_info -> {
                intent = Intent(this, COVIDinfoActivity::class.java)
            }
            R.id.timers -> {}
            R.id.diary -> {
                intent = Intent(this, DiaryActivity::class.java)
            }
        }
        if (intent != null){
            startActivity(intent)
        }
    }
}
