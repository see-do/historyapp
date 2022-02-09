package com.example.history

import androidx.annotation.RequiresPermission
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
        @PartMap postData : HashMap<String, RequestBody>
    ) : Call<StoryResponse>
}