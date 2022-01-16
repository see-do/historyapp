package com.example.history

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginEnterBtn.setOnClickListener{
            login()
        }
        binding.loginSignupTv.setOnClickListener {
            signUp()
        }
        binding.loginExitIv.setOnClickListener {
            exitLogin()
        }
        binding.loginIdEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(binding.loginIdEt.text.toString().isEmpty() || binding.loginIdEt.text.length > 10) {

                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun login(){
        if(binding.loginIdEt.text.toString().isEmpty() || binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this,"아이디나 비밀번호 입력", Toast.LENGTH_SHORT)?.show()
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
}