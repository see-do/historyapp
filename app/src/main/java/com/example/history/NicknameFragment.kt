package com.example.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.history.databinding.FragmentNicknameBinding

class NicknameFragment : Fragment() {
    lateinit var SignUpActivity : SignUpActivity
    lateinit var binding: FragmentNicknameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNicknameBinding.inflate(inflater, container, false)
        var SignUpActivity = SignUpActivity()
        binding.signupNicknameNextBtn.setOnClickListener {
            if (binding.signupNicknameEt.text.toString().isEmpty()) {
                binding.signupNicknameWarningIv.visibility = View.VISIBLE
                binding.signupNicknameWarningTv.visibility = View.VISIBLE
            }
            else {
                (context as SignUpActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.signup_frm, IdFragment())
                .commitAllowingStateLoss()
            }
        }
        return binding.root
    }
}