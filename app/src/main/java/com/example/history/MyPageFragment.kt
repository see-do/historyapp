package com.example.history

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentMypageBinding
import com.google.android.material.tabs.TabLayoutMediator
import android.view.WindowManager




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



        var myPageLoginFragment = MyPageLoginFragment()
        childFragmentManager.beginTransaction().replace(R.id.myPage_profile_ly,myPageLoginFragment).commit()

        fun toast(message:String){
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }





        binding.myPageSettingIv.setOnClickListener {
            val items = arrayOf("프로필 편집","잠금 화면 설정","로그아웃")
           val builder = AlertDialog.Builder(activity)
           // builder.setTitle(" ")
               builder.setItems(items){
                   dialog,which->toast("${items[which]}is selected")
               }

            val alertDialog = builder.create()
            val window = alertDialog.window
            window?.setGravity(Gravity.BOTTOM)

            alertDialog.show()


        }






        return binding.root
    }
}