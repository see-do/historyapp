package com.umc.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentMypageLogoutBinding

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