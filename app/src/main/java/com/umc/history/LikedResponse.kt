package com.umc.history

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class LikedResponse (
    @SerializedName("likeidx") val id : Int,
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("post")val post:Post?
        )
data class GetLikedStoryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("body") val body : LikedResponse?
)
data class Post(
    @SerializedName("contents") val contents : String,
    @SerializedName("title") val title : String,
    @SerializedName("category") val category : String,
    @SerializedName("user") val user : LikedUser?,
    @SerializedName("totalComment") val totalComment : Int,
    @SerializedName("totalClick") val totalClick : Int,
    @SerializedName("postIdx") val postIdx : Int,
    @SerializedName("totalLike") val totalLike : Int,
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,

)
data class LikedUser(
    @SerializedName("userId") val userIdx : Int,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("profileImgUrl") val profileImageUrl : String?,
)