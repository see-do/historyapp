package com.example.history

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthService {

    fun signUp(id:String?, nickname:String?, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com/").addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.signUp(User(id, nickname, password)).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("sign_onResponse", response.toString())
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("sign_onFailure",t.message.toString())
            }
        })
    }

    fun login(id:String, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com/").addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.login(id, password).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("login_onResponse", response.toString())
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("login_onFailure", t.message.toString())
            }
        })
    }
}