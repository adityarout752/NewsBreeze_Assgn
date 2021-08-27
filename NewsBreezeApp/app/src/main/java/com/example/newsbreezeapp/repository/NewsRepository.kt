package com.example.newsbreezeapp.repository

import com.example.newsbreezeapp.db.ArticleDatabase
import com.example.newsbreezeapp.modelData.Article
import com.example.newsbreezeapp.remote.RetrofitInstance


class NewsRepository(
    //to acces the function of database
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(country:String)=
        RetrofitInstance.api.getBreakingNews(country)



    //insert article in room database
    suspend fun upsert(article: Article)=db.getArticlesDao().upsert(article)

   fun getSavedNews()=db.getArticlesDao().getAllArticles()





}