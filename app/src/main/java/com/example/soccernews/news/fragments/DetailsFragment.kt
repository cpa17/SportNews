package com.example.soccernews.news.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.soccernews.R
import com.example.soccernews.news.data.NewsResponse
import com.example.soccernews.databinding.ItemDetailsBinding

class DetailsFragment: Fragment(R.layout.item_details) {

    private lateinit var binding: ItemDetailsBinding

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = ItemDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivShare.setOnClickListener {
                (arguments?.get("detail") as NewsResponse.Article).let {
                    val url = it.url.toString()
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, url)
                    startActivity(Intent.createChooser(shareIntent, "Teilen..."))
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

        binding.webViewButton.setOnClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main
                )
            val bundle = Bundle()
            (arguments?.get("detail") as NewsResponse.Article).let {
                bundle.putSerializable("url", it.url)
                bundle.putSerializable("source", it.source?.name)
            }

            navController.navigate(R.id.webViewFragment, bundle)
        }
    }
}