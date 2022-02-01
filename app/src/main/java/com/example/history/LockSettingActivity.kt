package com.example.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityLockSettingBinding
import com.example.history.databinding.ActivityTestBinding

class LockSettingActivity: AppCompatActivity() {
    private var mBinding: ActivityLockSettingBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLockSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}