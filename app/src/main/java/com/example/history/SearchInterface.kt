package com.example.history

import retrofit2.Call
import retrofit2.http.*

interface SearchInterface {
    @GET("/common/story/content/search")
    fun searchContents(
        @Query("keyword") keyword : String?) : Call<SearchResponse>

    @GET("/common/story/title/search")
    fun searchTitle(
        @Query("keyword") keyword : String?) : Call<SearchResponse>

}