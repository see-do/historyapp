package com.example.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.history.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding


    val information = arrayListOf("전체","한국사","동양사","서양사")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(inflater,container,false)

        val homeAdapter = HomeViewPagerAdapter(this)
        binding.homeMenuVp.adapter = homeAdapter
        TabLayoutMediator(binding.homeMenuTb,binding.homeMenuVp){
            tab,position->
            tab.text = information[position]
        }.attach()

        val bannerAdapter = BannerViewPagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.total_banner))
        bannerAdapter.addFragment(BannerFragment(R.drawable.korean_banner))
        bannerAdapter.addFragment(BannerFragment(R.drawable.western_banner))
        bannerAdapter.addFragment(BannerFragment(R.drawable.oriental_banner))
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.homeLoginIv.setOnClickListener {
            val intent = Intent(getActivity(), LoginActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }
}