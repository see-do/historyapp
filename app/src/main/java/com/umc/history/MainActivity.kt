package com.umc.history

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toDrawable

import com.umc.history.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() ,BottomNavigationView.OnNavigationItemSelectedListener{
    private var mBinding:ActivityMainBinding?=null
    private val binding get() = mBinding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMain.setOnNavigationItemSelectedListener(this)
        binding.bnvMain.itemIconTintList = null
        binding.bnvMain.selectedItemId= R.id.action_home
//        val intent = Intent(this, LockScreenService::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            this.startForegroundService(intent)
//        } else {
//            this.startService(intent)
//        }


    }

    fun basicAlert(item : Int){
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("작성중이던 내용이 사라져요")
            setMessage("다음에 이야기를 들려주실건가요?")
            setPositiveButton("떠나기"){ dialog, which->
                when(item){
                    R.id.action_writing -> {
                        var writeFragment = WriteFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, writeFragment, "write").commit()
                    }
                    R.id.action_test -> {
                        val intent = Intent(applicationContext, TestActivity::class.java)
                        startActivity(intent)

                    }
                    R.id.action_search -> {
                        var searchFragment = SearchFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, searchFragment).commit()

                    }
                    R.id.action_myPage -> {
                        var myPageFragment = MyPageFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, myPageFragment).commit()
                    }
                    R.id.action_home -> {
                        var homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, homeFragment).commit()
                    }
                }
            }
            setNegativeButton("남아있기", null)
            show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = supportFragmentManager.findFragmentByTag("write")
        if (fragment != null && fragment.isVisible) {
            basicAlert(item.itemId)
        } else {
            when (item.itemId) {
                R.id.action_writing -> {
                    val spf = getSharedPreferences("token",MODE_PRIVATE)
                    val token = spf.getString("accessToken", null)
                    if(token == null){
                        Toast.makeText(this,"글 작성을 위해 로그인을 해주세요",Toast.LENGTH_SHORT).show()
                        return false
                    }else{
                        var writeFragment = WriteFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, writeFragment, "write").commit()
                        return true
                    }
                }
                R.id.action_test -> {
                    val intent = Intent(applicationContext, TestActivity::class.java)
                    startActivity(intent)
                        return true

                }
                R.id.action_search -> {
                        var searchFragment = SearchFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, searchFragment).commit()
                        return true

                }
                R.id.action_myPage -> {
                        var myPageFragment = MyPageFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, myPageFragment).commit()
                        return true

                }
                R.id.action_home -> {
                        var homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, homeFragment).commit()
                        return true
                }
            }
        }
            return false
    }

}


