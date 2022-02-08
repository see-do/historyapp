package com.example.history

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LikedStoryInterface {
    @GET("/stories/Liking/{userId}")
    fun getLikedStory(@Path("userId")userId: String):Call<LikedResponse>
}