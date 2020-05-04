package org.sk.hopelife

import android.content.Context
import android.icu.util.ValueIterator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import kotlinx.android.synthetic.main.dis.view.*
import kotlin.reflect.KFunction

class RecordsAdapter(var items: ArrayList<ArrayList<String>>, val context: Context): BaseAdapter(){

    val inflater = LayoutInflater.from(context)
    var views = ArrayList<CheckBox>()
    var MODE = 0
    var pos = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val raw: View
        if (MODE == 0){
            raw = inflater.inflate(R.layout.record, parent, false)
            val text = raw.findViewById<TextView>(R.id.text)
            val date = raw.findViewById<TextView>(R.id.date)

            text.text = items[position][0]
            date.text = items[position][1]

            raw.setOnLongClickListener {
                MODE = 1
                pos = position
                notifyDataSetChanged()
                return@setOnLongClickListener true
            }

        }else{
            raw = inflater.inflate(R.layout.record_check, parent, false)
            val checkBox = raw.findViewById<CheckBox>(R.id.check)
            val text = raw.findViewById<TextView>(R.id.text)
            val date = raw.findViewById<TextView>(R.id.date)

            if (position == pos){
                checkBox.isChecked = true
            }

            text.text = items[position][0]
            date.text = items[position][1]
            checkBox.setOnClickListener {
                views.get(position).isChecked = checkBox.isChecked
                if (!any()){
                    MODE = 0
                    notifyDataSetChanged()
                }
            }
            views.add(checkBox)
        }

        return raw
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    fun any(): Boolean{
        views.forEach {
            if (it.isChecked == true && views.indexOf(it) < items.size){
                return true
            }
        }
        return false
    }

    override fun notifyDataSetChanged() {
        views.clear()
        super.notifyDataSetChanged()
    }


}
