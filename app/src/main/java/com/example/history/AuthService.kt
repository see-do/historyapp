package com.example.history

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthService {

    fun signUp(nickname:String?, id:String?, password:String){
        val retrofit = Retrofit.Builder().baseUrl("").addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)

    }

    fun login(id:String, pwd:String){

    }
}