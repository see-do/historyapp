package com.example.history

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthService {

    fun signUp(nickname:String?, id:String?, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com/").addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.signUp(User(nickname, id, password))
    }

    fun login(id:String, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com/").addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.login(id, password)
    }
}