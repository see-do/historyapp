package com.example.history

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.internal.Streams.parse
import okhttp3.MediaType
import okhttp3.MediaType.parse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import org.json.*

class StoryService {
    private lateinit var storyView : StoryView
    private lateinit var oneStoryView : OneStoryView
    private lateinit var deleteView : DeleteView

    fun setStoryView(storyView : StoryView){
        this.storyView = storyView
    }
    fun setOneStoryView(oneStoryView: OneStoryView){
        this.oneStoryView = oneStoryView
    }

    fun setDeleteView(deleteView : DeleteView){
        this.deleteView = deleteView
    }

    fun writeStory(token : String?, pathList : List<MultipartBody.Part?>, id : String, title: String,
                   category: String, contents: String, hashtagList : List<String>?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)
        val jsonArray = arrayListOf<String>()
        if((hashtagList?.isNotEmpty())!!){
            for(hashtag in hashtagList){
                jsonArray.add("\"$hashtag\"")
            }
        }
        val body = RequestBody.create(MultipartBody.FORM,"")
        val emptyPart = MultipartBody.Part.createFormData("imageList","",body)
        val emptyList = arrayListOf<MultipartBody.Part>()
        emptyList.add(emptyPart)

        val jsonObject = JSONObject("{\"userId\":\"${id}\",\"category\":\"${category}\",\"title\":\"${title}\",\"contents\":\"${contents}\",\"hashTags\":${jsonArray}}").toString()
        Log.d("write_","$jsonObject")
        val jsonBody = RequestBody.create(parse("application/json"),jsonObject)

        storyService.writeStory("Bearer $token", if(pathList.isEmpty()){
            emptyList
        } else{
              pathList
              }, jsonBody).enqueue(object : Callback<StoryResponse>{
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

        storyService.deleteStory("Bearer $token", storyIdx).enqueue(object : Callback<DeleteResponse>{
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                val resp = response.body()
                if(resp!!.body){
                    deleteView.onDeleteSuccess(resp.body)
                } else {
                    deleteView.onDeleteFailure()
                }
            }
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getStory(postIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStory(postIdx).enqueue(object : Callback<GetOneStoryResponse>{
            override fun onResponse(call: Call<GetOneStoryResponse>, response: Response<GetOneStoryResponse>) {
                Log.d("get_OnResponse","$response")
                val resp = response.body()
                oneStoryView.onStorySuccess(resp!!.status, resp.body)
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
                storyView.onStorySuccess(resp!!.status, resp.body)
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
                val resp = response.body()
                storyView.onStorySuccess(resp!!.status, resp.body)
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }
    fun getStoriesCategoryOrderByLike(category: String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesCategoryOrderByLike(category).enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                Log.d("getLike_OnResponse","$response")
                val resp = response.body()
                storyView.onStorySuccess(resp!!.status, resp.body)
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }
    fun getStoriesCategoryOrderByRecent(category: String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesCategoryOrderByRecent(category).enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                Log.d("getLike_OnResponse","$response")
                val resp = response.body()
                storyView.onStorySuccess(resp!!.status, resp.body)
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }


}