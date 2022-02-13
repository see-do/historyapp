package com.example.history

import android.media.session.MediaSession
import android.util.Log
import okhttp3.internal.http.HttpHeaders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class SearchService {
    private lateinit var searchView : SearchView
    fun setSearchView(searchView: SearchView){
        this.searchView = searchView
    }
    fun searchContents(token: String?, keyWord : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val searchService = retrofit.create(SearchInterface::class.java)
        searchService.searchContents("Bearer $token", keyWord).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                Log.d("search_onResponse", response.toString())
                val resp = response.body()?.body
                searchView.onSearchSuccess(resp)
                Log.d("search_onResponse2","${resp}")
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("search_onFailure", t.message.toString())
            }


        })
    }
    fun searchTitle(token: String?, keyWord : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val searchService = retrofit.create(SearchInterface::class.java)
        searchService.searchContents("Bearer $token", keyWord).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                Log.d("searchTitle_onResponse", response.toString())
                val resp = response.body()
                Log.d("search_onResponse2","${resp?.body}")
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("searchTitle_onFailure", t.message.toString())
            }


        })
    }
}