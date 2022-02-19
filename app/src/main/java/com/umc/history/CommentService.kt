package com.umc.history

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentService {
    private lateinit var commentView : CommentView
    private lateinit var postCommentView: PostCommentView

    fun setCommentView(commentView: CommentView){
        this.commentView = commentView
    }

    fun postCommentView(postCommentView: PostCommentView){
        this.postCommentView = postCommentView
    }

    fun getComments(postId : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val commentService = retrofit.create(CommentInterface::class.java)
        commentService.getComments(postId).enqueue(object : Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                Log.d("getLike_OnResponse","$response")
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202) {
                    commentView.onCommentSuccess(resp!!.status, resp.body)
                } else {
                    commentView.onCommentFailure()
                }
            }
            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                commentView.onCommentFailure()
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
                Log.d("postComment_OnResponse","$response")
                if(response.code() == 200 || response.code() == 202){
                    postCommentView.postCommentSuccess()
                } else {
                    postCommentView.postCommentFailure()
                }
            }
            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                postCommentView.postCommentFailure()
                Log.d("getLike_OnFailure","$t")
            }
        })
    }
}