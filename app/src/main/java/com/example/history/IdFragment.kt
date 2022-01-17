package com.example.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
        binding.signupIdEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.signupIdEt)
            }

        }
        return binding.root
    }
    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }
}