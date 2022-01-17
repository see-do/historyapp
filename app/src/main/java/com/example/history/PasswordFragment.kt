package com.example.history

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentIdBinding
import com.example.history.databinding.FragmentPasswordBinding
import org.w3c.dom.Text

class PasswordFragment : Fragment() {
    lateinit var binding : FragmentPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)

        binding.signupPwdConfirmBtn.setOnClickListener {
            if(binding.signupPwdEt.text.toString().isEmpty()){
                binding.signupPwdWarningIv.visibility = View.VISIBLE
                binding.signupPwdWarningTv.visibility = View.VISIBLE
            }
            else if(binding.signupPwdCheckEt.text.toString() != binding.signupPwdEt.text.toString()){
                binding.signupPwdCheckWarningIv.visibility = View.VISIBLE
                binding.signupPwdCheckWarningTv.visibility = View.VISIBLE
            }
            else {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return binding.root
    }
}