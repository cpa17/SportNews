package com.example.soccernews

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.soccernews.data.NewsResponse
import com.example.soccernews.databinding.ItemDetailsBinding
import com.example.soccernews.util.Constants
import com.orhanobut.hawk.Hawk

class DetailsFragment: Fragment(R.layout.item_details) {

    private lateinit var binding: ItemDetailsBinding
    var savedList: ArrayList<NewsResponse.Article> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = ItemDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            (arguments?.get("detail") as NewsResponse.Article).let { detail ->

                if (Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST) != null) {
                    (Hawk.get(Constants.FAVORITE_LIST) as ArrayList<NewsResponse.Article>).forEach {
                        if (it.url == detail.url) {
                            ivFavorite.setImageResource(R.drawable.ic_liked)
                            ivFavorite.setOnClickListener {
                                (arguments?.get("detail") as NewsResponse.Article).let { detail ->
                                    deleteFromFavorites(detail)
                                    ivFavorite.setImageResource(R.drawable.ic_like)
                                }
                            }
                        } else {
                            ivFavorite.setImageResource(R.drawable.ic_like)
                            ivFavorite.setOnClickListener {
                                (arguments?.get("detail") as NewsResponse.Article).let { detail ->
                                    ivFavorite.setImageResource(R.drawable.ic_liked)
                                    addToFavorites(detail)
                                }
                            }
                        }
                    }
                }
            }

            binding.apply {
                ivShare.setOnClickListener {
                    (arguments?.get("detail") as NewsResponse.Article).let {
                        val url = it.url.toString()
                        val shareIntent = Intent()
                        shareIntent.action = Intent.ACTION_SEND
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
                        startActivity(Intent.createChooser(shareIntent, "Share via"))
                    }
                }
            }

            (arguments?.get("detail") as NewsResponse.Article).let {
                binding.tvDetailsTitle.text = it.title
                binding.tvDetailsDescription.text = it.description
                binding.detailsSource.text = it.source?.name
                binding.detailsDate.text = it.publishedAt
                binding.backButton.setOnClickListener {
                    findNavController().navigate(R.id.action_navigation_details_to_navigation_news)
                }
                Glide.with(this@DetailsFragment).load(it.urlToImage).into(binding.ivNewsImage)
            }
        }
    }

    private fun addToFavorites(response: NewsResponse.Article) {
        savedList.add(response)
        if (Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST) != null) {
            if (Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST)
                    .contains(response).not()
            ) {
                Hawk.put(Constants.FAVORITE_LIST, savedList)
                savedList = Hawk.get(Constants.FAVORITE_LIST)
            }
        } else {
            Hawk.put(Constants.FAVORITE_LIST, savedList)
        }
    }

    private fun deleteFromFavorites(response: NewsResponse.Article) {
        savedList.add(response)
        if (Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST) != null) {
            Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST)
                .forEachIndexed { index, article ->
                    if (article == response) {
                        savedList.removeAt(index)
                    }
                }
            savedList = Hawk.get(Constants.FAVORITE_LIST)
            Hawk.put(Constants.FAVORITE_LIST, savedList)
        }
    }


    override fun onResume() {
        super.onResume()

        if (Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST) != null) {
            if (Hawk.get<ArrayList<NewsResponse.Article>>(Constants.FAVORITE_LIST).isNotEmpty()) {
                savedList = Hawk.get(Constants.FAVORITE_LIST)
            }
        }
    }
}