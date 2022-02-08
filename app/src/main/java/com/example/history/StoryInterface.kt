package com.example.history

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface StoryInterface {
    @GET()
    fun getStory()

    @Multipart
    @POST("/common/story")
    fun writeStory(
        @Part imageList : MultipartBody.Part?,
        @Part("userId") userId : String,
        @Part("category") category : String,
        @Part("title") title : String,
        @Part("contents") contents : String,
        @Part("hashTags") hashtagList : ArrayList<Hashtag?>
    ) : Call<StoryResponse>
}