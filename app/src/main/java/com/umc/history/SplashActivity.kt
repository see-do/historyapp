package com.umc.history


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SplashActivity:AppCompatActivity() {

    val SPLASH_VIEW_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, SPLASH_VIEW_TIME)
        val spf = getSharedPreferences("auto_login", Activity.MODE_PRIVATE)
        val id = spf.getString("id", null)
        val password = spf.getString("password", null)
        Log.d("auto", "auto_login")
//        if(id != null && password != null) {
//            autologin(id, password)
//        }

    }



    private fun autologin(id:String, password:String){
        val authService = AuthService()
        authService.login(id, password)
    }

}