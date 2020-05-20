package udit.programmer.co.firebase.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_layout.view.*
import udit.programmer.co.firebase.R

class MovieAdapter(val list: ArrayList<String>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = LayoutInflater.from(parent.context)
        val view: View
        view =
            convertView ?: inflator.inflate(R.layout.item_layout, parent, false)
        view.item_tv.text = list.elementAt(position)
        return view
    }

    override fun getItem(position: Int): Any = list.elementAt(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

}