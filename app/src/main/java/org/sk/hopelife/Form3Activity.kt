package org.sk.hopelife

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.google.android.material.textfield.TextInputEditText
import org.sk.hopelife.R.layout

class Form3Activity : AppCompatActivity() {

    var counter = 0
    var counter2 = 0

    val relativies = ArrayList<ArrayList<String>>()
    val disies = ArrayList<String>()
    val drugs = ArrayList<ArrayList<String>>()
    lateinit var doctor: MutableMap<String, String>

    var choosed = ArrayList<String>()

    val dis = arrayListOf(
        "СПИД", "Хроническая недостаточность коры надпочечников (болезнь Аддисона)", "Неспецифический язвенный колит", "Муковисцидоз", "Гемодиализные больные",
        "Периодическая болезнь", "Гиперплазия простаты", "Гипопаратиреоз", "Рассеянный склероз", "Люди после пересадки органов", "Болезнь Гоше", "Гемофилия",
        "Хроническая болезнь печени", "Хроническая сердечная недостаточность", "Множественная миелома", "Амиотрофический боковой склероз", "Болезнь Альцгеймера"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_form3)

        val linear_rel = findViewById<LinearLayout>(R.id.linear)
        val linear_drug = findViewById<LinearLayout>(R.id.linear_drug)
        val inflater: LayoutInflater = LayoutInflater.from(this)

        findViewById<ImageButton>(R.id.plus_dis).setOnClickListener {
            val builder = AlertDialog.Builder(this);
            val view: View = inflater.inflate(layout.choose_dialog, null, false)
            builder.setView(view)
            val listView = view.findViewById<ListView>(R.id.list)
            val adap = DisAdapter(dis, this)
            listView.adapter = adap
            builder.setPositiveButton("Ок", { dialog, which ->
                proccessChoosed(adap.choosed)
            })
            builder.setNegativeButton("Отмена", null)
            builder.create().show()
        }

        findViewById<ImageButton>(R.id.plus_rel).setOnClickListener {
            val rowView = inflater.inflate(layout.relative, null, false)
            counter++
            rowView.id = counter
            linear_rel.addView(rowView)
        }

        findViewById<ImageButton>(R.id.plus_drug).setOnClickListener {
            val rowView = inflater.inflate(layout.drug, null, false)
            counter2++
            rowView.id = counter2
            linear_drug.addView(rowView)
        }

        findViewById<Button>(R.id.next).setOnClickListener {
            if (checkAll()) {
                for (i in 1..counter){
                    val relative = arrayListOf(
                        linear_rel.findViewById<View>(i).findViewById<TextInputEditText>(R.id.name).text.toString(),
                        linear_rel.findViewById<View>(i).findViewById<TextInputEditText>(R.id.phone).text.toString()
                    )
                    relativies.add(relative)
                }

                for (i in 1..counter2){
                    val drug = arrayListOf(
                        linear_rel.findViewById<View>(i).findViewById<TextInputEditText>(R.id.title).text.toString(),
                        linear_rel.findViewById<View>(i).findViewById<TextInputEditText>(R.id.period).text.toString()
                    )
                    drugs.add(drug)
                }

                choosed.forEach {
                    disies.add(it)
                }

                doctor = mutableMapOf(
                    "name" to findViewById<TextInputEditText>(R.id.name1).text.toString(),
                    "phone" to findViewById<TextInputEditText>(R.id.phone1).text.toString()
                )

                val presure = findViewById<TextInputEditText>(R.id.presure).text.toString().toInt()
                val freq = findViewById<TextInputEditText>(R.id.frequency).text.toString().toInt()

                val show: Boolean = findViewById<CheckBox>(R.id.check).isChecked
                saveState(show)

                val info = intent.extras?.get("info") as ArrayList<Any>

                val user = User(info[0].toString(), info[1].toString(), info[2].toString(), info[3].toString(),
                                info[4].toString().toInt(), info[5].toString().toInt(), info[6].toString(), info[7].toString(),
                                relativies, disies, drugs, doctor, presure, freq)

                JSONHelper.loadUserToFireBase(user)

                val intent = Intent(this, COVIDTestAcrivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
                this.finish()
            }
        }

    }

    fun proccessChoosed(new: ArrayList<String>){
        val linear = findViewById<LinearLayout>(R.id.linear_dis)
        choosed = new
        linear.removeAllViews()
        new.forEach {
            val text = TextView(this).apply{
                text = it
            }
            linear.addView(text)
        }
    }

    fun checkAll(): Boolean{
        val f = findViewById<TextInputEditText>(R.id.name1).text.isNullOrEmpty()
        val s = findViewById<TextInputEditText>(R.id.phone1).text.isNullOrEmpty()
        val t = findViewById<TextInputEditText>(R.id.presure).text.isNullOrEmpty()
        val fo = findViewById<TextInputEditText>(R.id.frequency).text.isNullOrEmpty()

        if (counter == 0 || f or s or t or fo){
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun saveState(state: Boolean){
        val sharedPref: SharedPreferences = getSharedPreferences("state", 0)
        sharedPref.edit().putBoolean("show", state).apply()
    }
}
