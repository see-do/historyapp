package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentIdBinding

class IdFragment : Fragment() {
    lateinit var binding : FragmentIdBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIdBinding.inflate(inflater, container, false)
        binding.signupIdNextBtn.setOnClickListener {
            (context as SignUpActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.signup_frm, PasswordFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

}