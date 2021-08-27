package com.example.newsbreezeapp.remote





import com.example.newsbreezeapp.modelData.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country:String="us",
        @Query("apiKey")apikey:String="6953a7421bb749c589e687746c85eee8"
    ):Response<NewsResponse>

}