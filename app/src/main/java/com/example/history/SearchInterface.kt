package com.example.history

import retrofit2.Call
import retrofit2.http.*

interface SearchInterface {
    @GET("/common/story/content/search")
    fun searchContents(
        @Header("Authorization") token : String,
        @Query("keyword") keyword : String?) : Call<SearchResponse>

    @GET("/common/story/content/search")
    fun searchTitle(
        @Header("Authorization") token : String,
        @Query("keyword") keyword : String?) : Call<SearchResponse>

}