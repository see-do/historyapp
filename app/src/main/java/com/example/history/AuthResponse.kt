package com.example.history

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class AuthResponse(
    @SerializedName("timestamp") val timestamp : Timestamp,
    @SerializedName("status") val status : Int,
    @SerializedName("error") val error : String,
    @SerializedName("message") val message : String,
    @SerializedName("path") val path : String
)
