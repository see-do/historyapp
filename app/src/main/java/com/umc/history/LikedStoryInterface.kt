package com.umc.history

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface LikedStoryInterface {
    @GET("/stories/Liking")
    fun getLikedStory():Call<GetLikedStoryResponse>
}
