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
    fun getStory() {
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        //storyService.getStory().
    }

    fun writeStory(pathList : ArrayList<MultipartBody.Part?>){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)
//        val requestFile = RequestBody.create(MediaType.parse("application/jpeg"), file)
//        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        storyService.writeStory(pathList,"duck","korean","duck","duck",
            arrayListOf(Hashtag("duck"))).enqueue(object : Callback<StoryResponse>{
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                Log.d("write_onResponse","test")
            }
            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
            })
    }
}