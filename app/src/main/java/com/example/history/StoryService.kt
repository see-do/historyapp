package com.example.history

import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class StoryService {
    private lateinit var storyView : StoryView

    fun setStoryView(storyView : StoryView){
        this.storyView = storyView
    }

    fun writeStory(pathList : List<MultipartBody.Part?>?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        val userId = RequestBody.create(MediaType.parse("text/plain"),"duck")
        var hmap = HashMap<String, RequestBody>()
        hmap.put("userId", userId)
        hmap.put("category", userId)
        hmap.put("title", userId)
        hmap.put("contents", userId)
        hmap.put("hashTags", userId)

        storyService.writeStory(pathList,hmap).enqueue(object : Callback<StoryResponse>{
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                Log.d("write_onResponse","$response")
            }
            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                Log.d("write_onFail","$t")
            }
            })
    }
    fun deleteStory(token: String, storyIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.deleteStory("Bearer $token", 1).enqueue(object : Callback<DeleteResponse>{
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                Log.d("delete_Onresponse","$response")
            }
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getStory(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStory().enqueue(object : Callback<GetOneStoryResponse>{
            override fun onResponse(call: Call<GetOneStoryResponse>, response: Response<GetOneStoryResponse>) {
                Log.d("get_OnResponse","$response")

            }
            override fun onFailure(call: Call<GetOneStoryResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getStoriesAllOrderByRecent(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesAllOrderByRecent().enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                Log.d("getRecent_OnResponse","$response")
                val resp = response.body()
                storyView.onStorySuccess(resp!!.status, resp!!.body)
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getRecent_OnFailure","$t")
            }
        })
    }
    fun getStoriesAllOrderByLike(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesAllOrderByLike().enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                Log.d("getLike_OnResponse","$response")
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }
    fun getStoriesCategoryOrderByLike(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)
        //카테고리 수정 필요
        storyService.getStoriesCategoryOrderByLike("KOREAN").enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                Log.d("getLike_OnResponse","$response")
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }
    fun getStoriesCategoryOrderByRecent(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)
        //카테고리 수정 필요
        storyService.getStoriesCategoryOrderByRecent("KOREAN").enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                Log.d("getLike_OnResponse","$response")
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }

}