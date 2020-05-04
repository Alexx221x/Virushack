package org.sk.hopelife

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.zip.Inflater

class AnswersAdapter(var items: ArrayList<String>, val context: Context): BaseAdapter() {

    val inflater = LayoutInflater.from(context)
    var choosedPosition = -1
    var views = ArrayList<View>()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val raw = inflater.inflate(R.layout.answer, parent, false)
        val text = raw.findViewById<TextView>(R.id.text)
        text.text = items[position]

        raw.setOnClickListener {
            if (raw.background == context.getDrawable(R.drawable.answer_choosed)){
                raw.background = context.getDrawable(R.drawable.text_input)
            }else{
                setDisabled()
                raw.background = context.getDrawable(R.drawable.answer_choosed)
                choosedPosition = position
            }
        }

        views.add(raw)

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

    override fun notifyDataSetChanged() {
        views.clear()
        super.notifyDataSetChanged()

    }

    fun setDisabled(){
        views.forEach {
            it.background = context.getDrawable(R.drawable.text_input)
        }
    }
}