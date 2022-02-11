package com.example.history

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        val myPageAdapter = MyPageViewPagerAdapter(this)
        binding.myPageMenuVp.adapter = myPageAdapter
        TabLayoutMediator(binding.myPageMenuTb, binding.myPageMenuVp) { tab, position ->
            tab.text = information[position]
        }.attach()


        var myPageLoginFragment = MyPageLoginFragment()
        childFragmentManager.beginTransaction().replace(R.id.myPage_profile_ly, myPageLoginFragment)
            .commit()

        fun toast(message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

//        binding.myPageSettingLy.setOnClickListener {
//            val items = arrayOf("프로필 편집","잠금 화면 설정","로그아웃")
//            val builder = AlertDialog.Builder(activity)
//            builder.setItems(items){
//                    dialog,which->toast("${items[which]}is selected")
//            }
//
//            val alertDialog = builder.create()
//            val window = alertDialog.window
//            window?.setGravity(Gravity.BOTTOM)
//
//            alertDialog.show()
//

//        }
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog01, null)
        builder.setView(dialogView)



        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)

        builder.setView(dialogView)

        fun showDialog(){
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.dialog01_profile).setOnClickListener{
                val intent = Intent(activity,ProfileEditorActivity::class.java)
                startActivity(intent)
            }
//            alertDialog.findViewById<TextView>(R.id.dialog01_lock_setting).setOnClickListener{
//                val intent = Intent(activity,LockSettingActivity::class.java)
//                startActivity(intent)
//            }
            alertDialog.findViewById<TextView>(R.id.dialog01_logout).setOnClickListener{
                val intent = Intent(activity,SettingLogoutActivity::class.java)
                startActivity(intent)
            }


        }


        binding.myPageSettingLy.setOnClickListener {
           showDialog()
        }










        return binding.root
    }


}