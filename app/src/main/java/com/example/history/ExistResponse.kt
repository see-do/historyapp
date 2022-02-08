package com.example.history

import com.google.gson.annotations.SerializedName

data class ExistResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : Boolean
)