package com.example.soccernews.news.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccernews.news.viewmodels.BaseViewModel
import com.example.soccernews.news.data.NewsAPIService
import com.example.soccernews.news.data.NewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsfeedViewModel(
    application: Application
) : BaseViewModel(application) {

    private val newsAPIService = NewsAPIService()
    private val disposable = CompositeDisposable()

    private val _newsSearchLiveData = MutableLiveData<NewsResponse>()
    val newsSearchLiveData : LiveData<NewsResponse> = _newsSearchLiveData

    private val _breakingNewsLiveData = MutableLiveData<NewsResponse>()
    val breakingNewsLiveData : LiveData<NewsResponse> = _breakingNewsLiveData


    fun getBreakingNews(page: Int, country : String, category: String) {
        disposable.add(
            newsAPIService.getBreakingNews(page, country, category)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsResponse>() {
                    override fun onSuccess(response: NewsResponse) {
                        _breakingNewsLiveData.value = response
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )

    }
}