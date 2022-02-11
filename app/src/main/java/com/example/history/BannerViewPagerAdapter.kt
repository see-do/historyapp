package com.example.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllBannerFragment()
            1 -> KoreanBannerFragment()
            2 -> OrientalBannerFragment()
            else -> WesternBannerFragment()
        }
    }
}