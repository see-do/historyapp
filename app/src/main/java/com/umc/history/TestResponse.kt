package com.umc.history

import com.google.gson.annotations.SerializedName


data class GetTestResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : List<Test>?
)
data class Test(
    @SerializedName("quizIdx") val quizIdx : Int,
    @SerializedName("category") val category : String,
    @SerializedName("question") val question : String,
    @SerializedName("answer") val answer : Boolean,
    @SerializedName("solution") val solution : String
)