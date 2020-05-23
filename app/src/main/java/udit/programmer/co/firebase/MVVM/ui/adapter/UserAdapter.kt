package udit.programmer.co.firebase.MVVM.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import udit.programmer.co.firebase.R
import kotlinx.android.synthetic.main.item_user.view.*
import udit.programmer.co.firebase.MVVM.data.models.User

class UserAdapter(var data: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                username_tv.text = user.login;
                Picasso.get().load(user.url).into(image_view)
            }
        }
    }
}