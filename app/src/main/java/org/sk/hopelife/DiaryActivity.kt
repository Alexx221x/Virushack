package org.sk.hopelife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ListView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DiaryActivity : AppCompatActivity() {

    var items = ArrayList<ArrayList<String>>()
    lateinit var adapter: RecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        findViewById<ImageButton>(R.id.plus).setOnClickListener {
            val intent = Intent(this, CreateRecordActivity::class.java)
            startActivity(intent)
        }

        val list = findViewById<ListView>(R.id.list)
        adapter = RecordsAdapter(items, this)
        list.adapter = adapter
        grubFireBase()
    }

    fun grubFireBase() {
        items.clear()
        val email = Firebase.auth.currentUser?.email!!.split("@")[0]
        val db = Firebase.database.reference

        val count = application.applicationContext.getSharedPreferences("records", 0).getInt("id", -1)
        Log.i("fuck", count.toString())
        if (count != -1){
            for (i in 0..count){
                val snap = db.child("records").child("${email}${i}").ref
                snap.addValueEventListener(object  : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        Log.i("fuck", "canceled")
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val date = p0.child("date").value.toString()
                        val desc = p0.child("desc").value.toString()
                        val temp = p0.child("temp").value.toString()
                        val pres = p0.child("presure").value.toString()
                        items.add(arrayListOf(desc, date, temp, pres))
                        adapter.notifyDataSetChanged()
                    }

                })
            }
        }
    }
}
