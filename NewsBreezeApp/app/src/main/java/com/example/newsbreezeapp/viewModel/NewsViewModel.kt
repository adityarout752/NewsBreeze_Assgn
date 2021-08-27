package com.example.newsbreezeapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsbreezeapp.modelData.Article
import com.example.newsbreezeapp.modelData.NewsResponse
import com.example.newsbreezeapp.remote.Resource
import com.example.newsbreezeapp.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class NewsViewModel(var newsRepository: NewsRepository): ViewModel() {

    val breakingNews:MutableLiveData<Resource<NewsResponse>> =MutableLiveData()



    init{
        getBreakingNews("us")
    }
    
    fun getBreakingNews(country:String)=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response=newsRepository.getBreakingNews(country)
         breakingNews.postValue(handleBreakingNewsResponse(response))
        Log.d("adi","getbreakin:${response}")
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }



    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun getSavedNews()=newsRepository.getSavedNews()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        newsRepository.delete(article)
    }
}