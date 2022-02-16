package com.example.history

import android.util.Log
import okhttp3.MediaType.parse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentService {
    private lateinit var commentView : CommentView

    fun setCommentView(commentView: CommentView){
        this.commentView = commentView
    }

    fun getComments(postId : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val commentService = retrofit.create(CommentInterface::class.java)

        commentService.getComments(postId).enqueue(object : Callback<CommentResponse> {
            override fun onResponse(call: retrofit2.Call<CommentResponse>, response: Response<CommentResponse>) {
                Log.d("getLike_OnResponse","$response")
                val resp = response.body()
                commentView.onCommentSuccess(resp!!.status, resp.body)

            }
            override fun onFailure(call: retrofit2.Call<CommentResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }

    fun postComment(token : String, postId : Int, comment : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val commentService = retrofit.create(CommentInterface::class.java)

        commentService.postComment("Bearer $token", postId, comment).enqueue(object : Callback<StoryResponse> {
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                Log.d("getLike_OnResponse","$response")
                val resp = response.body()
            }
            override fun onFailure(call: retrofit2.Call<StoryResponse>, t: Throwable) {
                Log.d("getLike_OnFailure","$t")
            }
        })
    }
}