package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentAllBinding
import com.example.history.databinding.FragmentKoreanhistoryBinding

class KoreanHistoryFragment: Fragment() {
    lateinit var binding: FragmentKoreanhistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKoreanhistoryBinding.inflate(inflater,container,false)
        return binding.root
    }


}