package com.example.soccernews.newsfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.R
import com.example.soccernews.data.NewsResponse
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    val items = arrayListOf<NewsResponse.Article>()
    var newsItemClickListener: (NewsResponse.Article) -> Unit = {}
    var favoritesItemClickListener: (NewsResponse.Article) -> Unit = {}
    var savedList: ArrayList<NewsResponse.Article> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )

    override fun getItemCount() = items.count()
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class NewsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: NewsResponse.Article) {


            item.let {
                itemView.Title.text = item.title
                //itemView.Description.text = item.description
                itemView.Source.text = item.source?.name.toString()
                itemView.PublishedAt.text = item.publishedAt


                Glide.with(itemView).load(item.urlToImage).into(itemView.ArticleImage)

                itemView.setOnClickListener {
                    newsItemClickListener(item)
                }
            }
        }
    }

    fun setItems(response: ArrayList<NewsResponse.Article>) {
        items.addAll(response)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }
}
