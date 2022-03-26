package com.example.soccernews.newsfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.databinding.ItemPostBinding

class PostListAdapter: ListAdapter<Post, PostListAdapter.PostViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(ItemPostBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(holder.itemView).load(item.urlToImage).into(holder.binding.ArticleImage)
        with(holder.binding) {
            Title.text = item.title
            Source.text = item.source?.name
            PublishedAt.text = item.publishedAt
            Description.text = item.description
        }
    }

    class PostViewHolder(
        val binding: ItemPostBinding
    ): RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }


}
