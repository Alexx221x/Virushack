package org.sk.hopelife

import org.sk.hopelife.R.layout
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView


class DisAdapter(var items: ArrayList<String>, context: Context): BaseAdapter() {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    val choosed = ArrayList<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(layout.dis, parent, false)
        rowView.findViewById<TextView>(R.id.title).text = items[position]
        rowView.findViewById<CheckBox>(R.id.check).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                choosed.add(items[position])
            }else{
                choosed.remove(items[position])
            }
        }

        return rowView
    }

    override fun getItem(position: Int): Any {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}