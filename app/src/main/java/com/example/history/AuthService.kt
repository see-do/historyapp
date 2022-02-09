package com.example.history

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder
import java.net.URLEncoder

class AuthService {
    private lateinit var authView: AuthView

    fun setAuthView(authView: AuthView){
        this.authView = authView
    }
    fun nickNameExist(nickname: String?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.nickNameExist(nickname).enqueue(object : Callback<ExistResponse>{
            override fun onResponse(call: Call<ExistResponse>, response: Response<ExistResponse>) {
                Log.d("nickName_onResponse", response.toString())
                val resp = response.body()
                Log.d("nickName","${resp.toString()}")
            }
            override fun onFailure(call: Call<ExistResponse>, t: Throwable) {
                Log.d("nickName_onFailure", t.message.toString())
            }


        })
    }

    fun userIdExist(userId : String?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.userIdExist(userId).enqueue(object : Callback<ExistResponse>{
            override fun onResponse(call: Call<ExistResponse>, response: Response<ExistResponse>){
                Log.d("userId_onResponse", response.body()?.body.toString())
                val resp = response.body()
                authView.onAuthSuccess(resp!!.body)
                Log.d("onExist_retrofit","$resp")
            }
            override fun onFailure(call: Call<ExistResponse>, t: Throwable) {
                Log.d("userId_onFailure", t.message.toString())
            }
        })
    }
    fun signUp(id:String?, nickname:String?, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
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
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        Log.d("break","break")
        authService.login(User("duck",null, "duck")).enqueue(object : Callback<ExistResponse>{
            override fun onResponse(call: Call<ExistResponse>, response: Response<ExistResponse>) {
                Log.d("login_onResponse", response.toString())
            }
            override fun onFailure(call: Call<ExistResponse>, t: Throwable) {
                Log.d("login_onFailure", t.message.toString())
            }
        })
    }
}