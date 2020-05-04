package org.sk.hopelife

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class CreateRecordActivity : AppCompatActivity(), View.OnClickListener{

    val c = Calendar.getInstance()
    val yearNow = c.get(Calendar.YEAR)
    val monthNow = c.get(Calendar.MONTH)
    val dayNow = c.get(Calendar.DAY_OF_MONTH)

    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_record)

        findViewById<Button>(R.id.prev).setOnClickListener(this)
        findViewById<Button>(R.id.next).setOnClickListener(this)

        date = "${ if (dayNow < 10) 0 else ""}$dayNow.${ if (monthNow < 10) 0 else ""}$monthNow"
        findViewById<TextView>(R.id.date).text = date

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.date -> {
                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    date = "${ if (dayOfMonth < 10) 0 else ""}$dayOfMonth.${ if (month < 10) 0 else ""}$month"
                    findViewById<TextView>(R.id.date).text = date
                }, yearNow, monthNow, dayNow)

                dpd.show()
            }
            R.id.next -> {
                if (check()){
                    val temp = findViewById<TextView>(R.id.temp).text.toString().toFloat()
                    val presure = findViewById<TextView>(R.id.pres).text.toString()
                    val desctiption = findViewById<TextView>(R.id.desc).text.toString()
                    val email = Firebase.auth.currentUser?.email

                    val record = Record(date, temp, presure, desctiption, email)
                    val sp = application.applicationContext.getSharedPreferences("records", 0)
                    var id = sp.getInt("id", -1)
                    id++
                    JSONHelper.loadRecordToFireBase(record, id)
                    sp.edit().putInt("id", id).apply()

                    this.finish()
                    onBackPressed()
                }
            }
            R.id.prev -> {
                this.finish()
                onBackPressed()
            }
        }
    }

    fun check(): Boolean{
        val f = findViewById<TextView>(R.id.temp).text.isNullOrEmpty()
        val s = findViewById<TextView>(R.id.pres).text.isNullOrEmpty()
        val t = findViewById<TextView>(R.id.desc).text.isNullOrEmpty()
        if(f or s or t){
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}
