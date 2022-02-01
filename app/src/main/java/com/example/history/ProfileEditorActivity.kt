package com.example.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history.databinding.ActivityLockSettingBinding
import com.example.history.databinding.ActivityProfileEditorBinding

class ProfileEditorActivity: AppCompatActivity() {
    private var mBinding: ActivityProfileEditorBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityProfileEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}