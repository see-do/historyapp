package com.example.history

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface StoryInterface {
    @GET()
    fun getStory()

    @Multipart
    @POST()
    fun writeStory(
        @Part userId : String,
        @Part category : String,
        @Part title : String,
        @Part contents : String,
        @Part hashtagList : ArrayList<Hashtag?>,
        @Part imageList : MultipartBody.Part?,
    )
}