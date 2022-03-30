package com.example.soccernews.forum.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.soccernews.R
import com.example.soccernews.databinding.PostDetailsBinding
import com.example.soccernews.forum.data.ForumDatabase
import com.example.soccernews.forum.data.ForumResponse
import kotlinx.coroutines.launch

class DetailsFragment: Fragment(R.layout.post_details) {

    private lateinit var binding: PostDetailsBinding
    private var postId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = PostDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivDelete.setOnClickListener {
                (arguments?.get("detail") as ForumResponse.Post).let {
                    postId = it.id!!
                    deleteNote()

                }
            }
        }

        (arguments?.get("detail") as ForumResponse.Post).let {
            binding.tvDetailsTitle.text = it.title
            binding.tvDetailsDescription.text = it.content
            binding.backButton.setOnClickListener {
                findNavController().navigate(R.id.action_detailsPost_to_navigation_forum)
            }
        }
    }

    private fun deleteNote(){
        lifecycleScope.launch {
            context?.let {
                ForumDatabase.getDatabase(it).forumDao().deleteSpecificPost(postId)
                findNavController().navigate(R.id.action_detailsPost_to_navigation_forum)
            }
        }
    }
}