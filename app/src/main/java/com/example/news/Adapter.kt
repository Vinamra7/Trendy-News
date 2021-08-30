package com.example.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class Adapter(private val listner: NewsItemClicked): RecyclerView.Adapter<ViewHold>() {
    private val items: ArrayList<newss> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent,false)
        val viewholder = ViewHold(view)
        view.setOnClickListener{
        listner.onItemClicked(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val currentItem = items[position]
        holder.titleview.text= currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.iamge)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<newss>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleview: TextView = itemView.findViewById(R.id.title)
    val iamge:ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)

}

interface NewsItemClicked{
    fun onItemClicked(item: newss)
}