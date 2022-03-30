package com.example.soccernews.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.soccernews.R
import kotlinx.android.synthetic.main.fragment_create_post.*
import kotlinx.coroutines.launch

class PostFragment : Fragment(R.layout.fragment_create_post) {

    private var postId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkButton.setOnClickListener {
            if (postId != -1){
                updatePost()
            }else{
                savepost()
            }
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_createPost_to_navigation_forum)
        }

    }

    private fun updatePost(){
        lifecycleScope.launch {

            context?.let {
                var posts = ForumDatabase.getDatabase(it).forumDao().getSpecificPost(postId)

                posts.title = PostTitle.text.toString()
                posts.content = PostText.text.toString()

                ForumDatabase.getDatabase(it).forumDao().updatePost(posts)
                PostTitle.setText("")
                PostText.setText("")
                findNavController().navigate(R.id.action_createPost_to_navigation_forum)
            }
        }
    }

    private fun savepost(){

        if (PostTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Post Title is Required", Toast.LENGTH_SHORT).show()
        }
        else if (PostText.text.isNullOrEmpty()){

            Toast.makeText(context,"Post Text is Required", Toast.LENGTH_SHORT).show()
        }

        else{
            lifecycleScope.launch {
                var posts = ForumResponse.Post()
                posts.title = PostTitle.text.toString()
                posts.content = PostText.text.toString()
                context?.let {
                    ForumDatabase.getDatabase(it).forumDao().insertPost(posts)
                    PostTitle.setText("")
                    PostText.setText("")
                    findNavController().navigate(R.id.action_createPost_to_navigation_forum)
                }
            }
        }

    }

    private fun deleteNote(){

        lifecycleScope.launch {
            context?.let {
                ForumDatabase.getDatabase(it).forumDao().deleteSpecificPost(postId)
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

}