package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentMypageLoginBinding
import com.example.history.databinding.FragmentMypageLogoutBinding

class MyPageLogoutFragment : Fragment() {
    lateinit var binding: FragmentMypageLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageLogoutBinding.inflate(inflater, container, false)





        return binding.root
    }
}