package com.example.history

import retrofit2.Call
import retrofit2.http.*

interface AuthInterface {
    @GET("/user/sign/nickNameExist/{nickName}")
    fun nickNameExist(@Path("nickName")nickName:String?) : Call<ExistResponse>

    @GET("/user/sign/{userId}/exist")
    fun userIdExist(@Path("userId")userId:String?) : Call<ExistResponse>

    @POST("/user/sign")
    fun signUp(@Body user : User) : Call<AuthResponse>

    @POST("/user/login")
    fun login(@Body user : User) : Call<LoginResponse>

}