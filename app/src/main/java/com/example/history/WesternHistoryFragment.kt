package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentAllBinding
import com.example.history.databinding.FragmentWesternhistoryBinding

class WesternHistoryFragment: Fragment() {
    lateinit var binding: FragmentWesternhistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWesternhistoryBinding.inflate(inflater,container,false)
        return binding.root
    }


}