package com.example.history

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST("/user/sign")
    fun signUp(@Body user : User) : Call<AuthResponse>

    @POST()
    fun login(@Body id:String, password:String) : Call<AuthResponse>

}