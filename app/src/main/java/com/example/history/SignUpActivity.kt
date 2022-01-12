package com.example.history

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignupBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.signup_frm, NicknameFragment())
        transaction.commit()

        binding.signupNextBtn.setOnClickListener{
            changeFragment()
        }
    }
    private fun changeFragment(){
        when (count) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.signup_frm, IdFragment())
                    .commit()
                count++
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.signup_frm, PasswordFragment())
                    .commit()
                count++
            }
            2 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                count = 0
            }
        }
    }
}