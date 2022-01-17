package com.example.history

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity:AppCompatActivity() {
    val SPLASH_VIEW_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, SPLASH_VIEW_TIME)
        val pref = getSharedPreferences("auto_login", Activity.MODE_PRIVATE)
        val id = pref.getString("id", null)
        val pw = pref.getString("pw", null)

    }

    private fun autologin(){

    }
}