package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentAllBinding
import com.example.history.databinding.FragmentOrientalhistoryBinding

class OrientalHistoryFragment : Fragment() {
    lateinit var binding: FragmentOrientalhistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrientalhistoryBinding.inflate(inflater,container,false)
        return binding.root
    }


}