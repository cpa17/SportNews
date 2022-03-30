package com.example.soccernews.forum

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.R
import com.example.soccernews.news.data.NewsResponse
import kotlinx.android.synthetic.main.forum_post.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class ForumListAdapter : RecyclerView.Adapter<ForumListAdapter.ForumViewHolder>() {
    var postItemClickListener: (ForumResponse.Post) -> Unit = {}
    private val items = arrayListOf<ForumResponse.Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForumViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.forum_post, parent, false)
    )

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ForumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: ForumResponse.Post) {

            item.let {
                itemView.title_text_view.text = item.title
                itemView.body_text_view.text = item.content
            }

            itemView.setOnClickListener {
                postItemClickListener(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(response: ArrayList<ForumResponse.Post>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

}