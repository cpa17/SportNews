package com.example.soccernews.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.soccernews.R
import com.example.soccernews.databinding.FragmentForumBinding
import com.example.soccernews.news.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_forum.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ForumFragment : BaseFragment() {

    private var forumListAdapter = ForumListAdapter()

    override fun onCreateView(
        inflater:   LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_forum_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        launch {
            context?.let {
                var posts = ForumDatabase.getDatabase(it).forumDao().getAllPosts()
                forumListAdapter.updateItems(posts as ArrayList<ForumResponse.Post>)
                rv_forum_list.adapter = forumListAdapter
            }
        }

        floating_action_button.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_forum_to_createPost)
        }

        forumListAdapter.postItemClickListener = {
            findNavController().navigate(R.id.action_navigation_forum_to_createPost,
                bundleOf("detail" to it))
        }

    }
}