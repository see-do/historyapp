package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentMypageBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment : Fragment() {

    val information = arrayListOf("내 이야기","좋아하는 이야기")
    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMypageBinding.inflate(inflater,container,false)

        val myPageAdapter = MyPageViewPagerAdapter(this)
        binding.myPageMenuVp.adapter = myPageAdapter
        TabLayoutMediator(binding.myPageMenuTb,binding.myPageMenuVp){
                tab,position->
            tab.text = information[position]
        }.attach()



        return binding.root
    }
}