package com.example.history

import android.util.Log
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LikeService {

    fun postLike(token : String, postIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val likeService = retrofit.create(LikeInterface::class.java)
        likeService.postLike("Bearer $token",postIdx).enqueue(object : Callback<DeleteResponse> {
            override fun onResponse(call: retrofit2.Call<DeleteResponse>, response: retrofit2.Response<DeleteResponse>) {
                Log.d("post_LikeResponse","$response")
                val resp = response.body()
            }
            override fun onFailure(call: retrofit2.Call<DeleteResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}