package com.example.soccernews.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.R
import com.example.soccernews.databinding.FragmentNewsfeedBinding
import com.example.soccernews.newsfeed.NewsAdapter
import com.example.soccernews.news.viewmodels.NewsfeedViewModel
import kotlinx.android.synthetic.main.fragment_newsfeed.*

class NewsfeedFragment : Fragment(R.layout.fragment_newsfeed) {

    private lateinit var viewModel: NewsfeedViewModel
    private lateinit var binding: FragmentNewsfeedBinding
    private var newsAdapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentNewsfeedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NewsfeedViewModel::class.java]

        viewModel.getBreakingNews(1,"de", "sports")

        viewModel.breakingNewsLiveData.observe(viewLifecycleOwner) {
            it.articles?.let { it1 -> newsAdapter.setItems(it1) }
        }


        binding.apply {
            rv_news_list.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rv_news_list.adapter = newsAdapter
        }

        newsAdapter.newsItemClickListener = {
            findNavController().navigate(R.id.action_navigation_news_to_navigation_details,
                bundleOf("detail" to it))
        }
    }
}