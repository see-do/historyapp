package com.example.history

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), AuthView {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginPwEt.onFocusChangeListener = View.OnFocusChangeListener { p0, p1 ->
            if(p1){

            } else{
                hideKeyboard(binding.loginPwEt)
            }
        }

        binding.loginEnterBtn.setOnClickListener{
            login()
        }
        binding.loginSignupTv.setOnClickListener {
            signUp()
        }
        binding.loginExitIv.setOnClickListener {
            exitLogin()
        }

    }

    override fun onAuthFailure() {
        TODO("Not yet implemented")
    }

    override fun onAuthLoading() {
        TODO("Not yet implemented")
    }

    override fun onAuthSuccess(body: Boolean) {
        Log.d("login","login")
        if(!body){
            Toast.makeText(this,"아이디나 비밀번호 입력", Toast.LENGTH_SHORT)?.show()
        }
    }

    private fun login(){
        if(binding.loginIdEt.text.toString().isEmpty() || binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this,"아이디나 비밀번호 입력", Toast.LENGTH_SHORT)?.show()
        }
        else {
            Log.d("break","break")
            val authService = AuthService()
            authService.setAuthView(this)
            Log.d("break","break")
            authService.login(binding.loginIdEt.text.toString(), binding.loginPwEt.text.toString())
            val spf = getSharedPreferences("auto_login", Activity.MODE_PRIVATE)
            val editor = spf.edit()
            editor.putString("id",binding.loginIdEt.text.toString())
            editor.putString("password",binding.loginPwEt.text.toString())
            editor.apply()
            exitLogin()
        }
    }
    private fun signUp(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun exitLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun hideKeyboard(editText: EditText){
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }
}