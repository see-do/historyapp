package com.example.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentNicknameBinding

class NicknameFragment : Fragment(), ExistView {
    lateinit var binding: FragmentNicknameBinding
    private var existFlag = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNicknameBinding.inflate(inflater, container, false)

        binding.signupNicknameEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.signupNicknameEt)
            }

        }

        binding.signupNicknameCheckTv.setOnClickListener {
            checkExist()
        }

        binding.signupNicknameNextBtn.setOnClickListener {
            if(existFlag){
                showWarning("중복체크 버튼을 눌러주세요")
            } else
            if (binding.signupNicknameEt.text.toString().isEmpty()) {
                showWarning("닉네임을 입력해주세요")
            } else if (binding.signupNicknameEt.length() < 2){
                showWarning("닉네임은 2글자 이상, 10글자 미만이어야합니다.")
            } else {
                var idFragment = IdFragment()
                var bundle = Bundle()
                bundle.putString("nickname",binding.signupNicknameEt.text.toString())
                idFragment.arguments = bundle

                (context as SignUpActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.signup_frm, idFragment)
                .commit()
            }
        }
        return binding.root
    }
    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun showWarning(message : String){
        binding.signupNicknameWarningIv.visibility = View.VISIBLE
        binding.signupNicknameWarningTv.visibility = View.VISIBLE
        binding.signupNicknameWarningTv.text = message
    }

    override fun onAuthFailure() {
        TODO("Not yet implemented")
    }

    override fun onAuthLoading() {
        TODO("Not yet implemented")
    }

    override fun onAuthSuccess(body: Boolean) {
        Log.d("onExist_Success","$body")
        existFlag = body
        when(existFlag){
            false ->{
                binding.signupNicknameWarningTv.visibility = View.INVISIBLE
                binding.signupNicknameWarningIv.visibility = View.INVISIBLE
                binding.signupNicknameCheckExistTv.visibility = View.VISIBLE
            }
            true ->{
                showWarning("중복입니다")
            }
        }
    }

    private fun checkExist(){
        val authService = AuthService()
        authService.setExistView(this)
        authService.nickNameExist(binding.signupNicknameEt.text.toString())
    }
}