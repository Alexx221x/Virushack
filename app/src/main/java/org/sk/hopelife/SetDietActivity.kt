package org.sk.hopelife

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class SetDietActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    var startH = -1
    var endH = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_diet)

        val spinner = findViewById<Spinner>(R.id.spinner1)
        val diets = arrayListOf("Диета 1", "Диета 2", "Диета 3")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diets)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        findViewById<Button>(R.id.prev).setOnClickListener(this)
        findViewById<TextView>(R.id.first).setOnClickListener(this)
        findViewById<TextView>(R.id.last).setOnClickListener(this)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position){
            0 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet1))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance())
                findViewById<TextView>(R.id.freq).text = "2"
            }
            1 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet2))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "3"
            }
            2 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet3))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "4"
            }
            3 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet4))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "2"
            }
            4 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet5))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "2"
            }
            5 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet6))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "4"
            }
            6 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet7))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "4"
            }
            7 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet8))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "4"
            }
            8 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet9))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "4"
            }
            9 -> {
                val text = findViewById<TextView>(R.id.desc)
                val desc = Html.fromHtml(getString(R.string.diet10))
                text.setText(desc)
                text.setMovementMethod(LinkMovementMethod.getInstance());
                findViewById<TextView>(R.id.freq).text = "4"
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.prev -> {
                this.finish()
                onBackPressed()
            }
            R.id.first -> {
                val c = Calendar.getInstance()
                val hourNow = c.get(Calendar.HOUR_OF_DAY)
                val minNow = c.get(Calendar.MINUTE)

                val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    if (hourOfDay < endH || endH == -1){
                        findViewById<TextView>(R.id.first).text = "${if (hourOfDay < 10) 0 else ""}$hourOfDay:${if (minute < 10) 0 else ""}$minute"
                        startH = hourOfDay
                    }else{
                        Toast.makeText(this, "Выберите правильное время", Toast.LENGTH_SHORT).show()
                    }
                }, hourNow, minNow, true)
                tpd.show()
            }
            R.id.last -> {
                val c = Calendar.getInstance()
                val hourNow = c.get(Calendar.HOUR_OF_DAY)
                val minNow = c.get(Calendar.MINUTE)

                val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    if (hourOfDay > startH || startH == -1){
                        findViewById<TextView>(R.id.last).text = "${if (hourOfDay < 10) 0 else ""}$hourOfDay:${if (minute < 10) 0 else ""}$minute"
                        endH = hourOfDay
                    }else{
                        Toast.makeText(this, "Выберите правильное время", Toast.LENGTH_SHORT).show()
                    }
                }, hourNow, minNow, true)
                tpd.show()
            }
        }
    }
}
