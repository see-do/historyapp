package com.example.history

import android.net.Uri
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.sql.Timestamp

data class StoryResponse(
    @SerializedName("timestamp") val timestamp : Timestamp,
    @SerializedName("status") val status : Int,
    @SerializedName("error") val error : String,
    @SerializedName("message") val message : String,
    @SerializedName("path") val path : String
)
//nullable 수정 필요
data class GetOneStoryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("body") val body: OneStory?
)
//nullable 수정 필요
data class GetAllStoryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("body") val body : List<OneStory>
)
data class OneStory(
    @SerializedName("id") val id : Int,
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("postIdx") val postIdx : Int,
    @SerializedName("user") val path : StoryGetUser?,
    @SerializedName("category") val category : String,
    @SerializedName("title") val title : String,
    @SerializedName("contents") val contents : String,
    @SerializedName("totalLike") val totalLike : Int,
    @SerializedName("totalClick") val totalClick : Int,
    @SerializedName("totalComment") val totalComment : Int,
    @SerializedName("images") val images : List<StoryGetImage>?,
    @SerializedName("hashTags") val hashTags : List<StoryGetHashtag>?
)
data class StoryGetImage(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("imgIdx") val imgIdx : Int,
    @SerializedName("post") val post : Int,
    @SerializedName("imgUrl") val imageUrl : String
)
data class StoryGetUser(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("userId") val userId : String,
    @SerializedName("profileImgUrl") val profileImageUrl : String?,
    @SerializedName("authority") val authority : String
)
data class StoryGetHashtag(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("hashTagIdx") val hashTagIdx : Int,
    @SerializedName("post") val post : Int,
    @SerializedName("tag") val tag : String
)

data class DeleteResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : Boolean
)

data class CommentResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : List<Comment?>
)
data class Comment(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("commentIdx") val commentIdx : Int,
    @SerializedName("user") val commentUser : CommentUser,
    @SerializedName("contents") val contents : String
)
data class CommentUser(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("userId") val userId : String,
    @SerializedName("profileImgUrl") val profileImageUrl : String?,
    @SerializedName("authority") val authority : String
)
data class WriteStory(
    @SerializedName("userId") val userId : String,
    @SerializedName("category") val category : String,
    @SerializedName("title") val title : String,
    @SerializedName("contents") val contents : String,
    @SerializedName("hashTags") val hashTags:  String
)
