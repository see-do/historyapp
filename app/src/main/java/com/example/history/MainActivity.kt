package com.example.history

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.history.databinding.ActivityMainBinding
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
                        Log.d("das","dasd")
                        var testFragment = TestFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, testFragment).commit()
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
                    Log.d("das","dasd")
                    var writeFragment = WriteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, writeFragment, "write").commit()
                    return true
                }
                R.id.action_test -> {
                    Log.d("das","dasd")
                        var testFragment = TestFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, testFragment).commit()
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


