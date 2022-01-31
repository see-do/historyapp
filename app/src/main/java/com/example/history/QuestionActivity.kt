package com.example.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityQuestionBinding
import com.example.history.databinding.ActivityTestBinding

class QuestionActivity: AppCompatActivity(){
    private var mBinding: ActivityQuestionBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionExitLy.setOnClickListener{
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }





    }


}