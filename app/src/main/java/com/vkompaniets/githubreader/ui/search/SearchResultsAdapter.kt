package com.vkompaniets.githubreader.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.model.User

class SearchResultsAdapter(private val listener: UserClickListener) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private var items = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun setItems(items: List<User>){
        this.items = items
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        private val name = itemView.findViewById<TextView>(R.id.name)

        init {
            itemView.setOnClickListener { listener.onUserClicked(items[adapterPosition].login) }
        }

        fun bind(user: User) {
            Picasso.get().load(user.avatar).into(avatar)
            name.text = user.login
        }
    }

    interface UserClickListener {
        fun onUserClicked(login: String)
    }

}

