package com.devtides.androidcoroutinesflow.network

import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import retrofit2.http.GET

interface NewsService {

    @GET("news.json")
    suspend fun getNews(): List<NewsArticle>
}