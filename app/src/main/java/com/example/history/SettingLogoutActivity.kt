package com.example.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityLockSettingBinding
import com.example.history.databinding.ActivitySettingLogoutBinding

class SettingLogoutActivity : AppCompatActivity() {
    private var mBinding: ActivitySettingLogoutBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySettingLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}