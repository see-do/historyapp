package com.example.history

import retrofit2.http.Body
import retrofit2.http.GET

interface ProfileInterface {
    @GET
    fun getProfile(@Body nickname:String,follower: Int, following: Int,profileImg:String)
}