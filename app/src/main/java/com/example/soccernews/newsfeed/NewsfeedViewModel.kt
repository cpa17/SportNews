package com.example.soccernews.newsfeed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccernews.api.NewsRepository
import kotlinx.coroutines.launch

class NewsfeedViewModel(
    private val postRepository: NewsRepository
) : ViewModel() {

    val items = MutableLiveData<List<Post>>(emptyList())

    init {
        viewModelScope.launch {
            val posts = postRepository.getBreakingNews().body()!!.articles
            items.value = posts
        }
    }
}