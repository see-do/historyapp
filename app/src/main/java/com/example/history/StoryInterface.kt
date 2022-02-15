package com.example.history

import androidx.annotation.RequiresPermission
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface StoryInterface {
    @GET("/common/story/1")
    fun getStory() : Call<GetOneStoryResponse>

    @GET("/common/stories/recent/all")
    fun getStoriesAllOrderByRecent() : Call<GetAllStoryResponse>

    @GET("/common/stories/liking/all")
    fun getStoriesAllOrderByLike() : Call<GetAllStoryResponse>

    @GET("/common/stories/recent/{category}")
    fun getStoriesCategoryOrderByRecent(
        @Path("category") category: String
    ) : Call<GetAllStoryResponse>

    @GET("/common/stories/liking/{category}")
    fun getStoriesCategoryOrderByLike(
        @Path("category") category: String
    ) : Call<GetAllStoryResponse>

    @Multipart
    @POST("/common/story")
    fun writeStory(
        @Header("Authorization") Authorization: String,
        @Part imageList : List<MultipartBody.Part>?,
        @Part("postData") postData : RequestBody
    ) : Call<StoryResponse>

    @DELETE("common/story/delete/{storyIdx}")
    fun deleteStory(
        @Header("Authorization") Authorization : String,
        @Path("storyIdx") storyIdx : Int
    ) : Call<DeleteResponse>

    @GET("common/story/comment/{postId}")
    fun getComments(
        @Path("postId") postId : Int
    ) : Call<CommentResponse>
}