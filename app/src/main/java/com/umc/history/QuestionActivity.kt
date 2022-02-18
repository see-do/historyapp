package com.umc.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivityQuestionBinding

class QuestionActivity: AppCompatActivity(){
    private var mBinding: ActivityQuestionBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionExitLy.setOnClickListener{
            val intent = Intent(applicationContext,TestActivity::class.java)
            startActivity(intent)
        }





    }


}