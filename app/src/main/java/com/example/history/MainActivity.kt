package com.example.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_writing->{
                var writeFragment = WriteFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container,writeFragment).commit()
                return true
            }
            R.id.action_test->{
                var testFragment = TestFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container,testFragment).commit()
                return true
            }
            R.id.action_search->{
                var searchFragment = SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container,searchFragment).commit()
                return true
            }
            R.id.action_myPage->{
                var myPageFragment = MyPageFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container,myPageFragment).commit()
                return true
            }
            R.id.action_home->{
                var homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container,homeFragment).commit()
                return true
            }


        }
        return false

    }

}
