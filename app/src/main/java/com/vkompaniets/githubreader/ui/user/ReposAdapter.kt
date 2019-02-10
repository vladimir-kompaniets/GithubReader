package com.vkompaniets.githubreader.ui.user

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.model.Repo

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ViewHolder>(){

    private var items = emptyList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ReposAdapter.ViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun setItems(items: List<Repo>){
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val name = itemView.findViewById<TextView>(R.id.name)
        private val language = itemView.findViewById<TextView>(R.id.language)
        private val forks = itemView.findViewById<TextView>(R.id.forks)
        private val stars = itemView.findViewById<TextView>(R.id.stars)

        fun bind(repo: Repo){
            name.text = repo.name
            language.text = repo.language
            forks.text = repo.forks.toString()
            stars.text = repo.stars.toString()
        }


    }
}
