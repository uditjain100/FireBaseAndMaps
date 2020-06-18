package udit.programmer.co.firebase.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import udit.programmer.co.firebase.R

class MovieAdapter(val moviesn: ArrayList<Movies>) : RecyclerView.Adapter<MovieViewHolder>() {

    var onItemClickListener: MovieOnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_course_card, parent, false)
        return MovieViewHolder(itemview)
    }

    override fun getItemCount(): Int = moviesn.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesn[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(moviesn[position])
        }
    }
}