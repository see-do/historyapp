package com.umc.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.umc.history.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class HomeFragment: Fragment(){
    lateinit var binding: FragmentHomeBinding
    private var token : String? = null
    val information = arrayListOf("전체","한국사","동양사","서양사")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(inflater,container,false)

        val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        token = spf?.getString("accessToken", null)
        if(token != null){
            binding.homeLoginTv.visibility = View.VISIBLE
            binding.homeLoginIv.visibility = View.GONE
        }

        val homeAdapter = HomeViewPagerAdapter(this)
        binding.homeMenuVp.adapter = homeAdapter
        TabLayoutMediator(binding.homeMenuTb,binding.homeMenuVp){
            tab,position->
            tab.text = information[position]
        }.attach()


        val bannerAdapter = BannerViewPagerAdapter(this)
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.homeLoginIv.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.homeLoginTv.setOnClickListener {
            logout()
            binding.homeLoginTv.visibility = View.GONE
            binding.homeLoginIv.visibility = View.VISIBLE
        }



        return binding.root
    }
    fun getUser(context : Context) : String?{
        val spf = context.getSharedPreferences("accessToken",AppCompatActivity.MODE_PRIVATE)
        return spf.getString("accessToken", null)
    }
    private fun logout() {
        token = null
        val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        val editor = spf!!.edit()
        editor.clear()
        editor.commit()
        val userInfo = activity?.getSharedPreferences("user",AppCompatActivity.MODE_PRIVATE)
        val userEditor = userInfo!!.edit()
        userEditor.clear()
        userEditor.commit()
    }


}