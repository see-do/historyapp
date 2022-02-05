package com.example.history

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST()
    fun signUp(@Body nickname:String, id:String, password:String)

    @POST()
    fun login(@Body id:String, password:String)

}