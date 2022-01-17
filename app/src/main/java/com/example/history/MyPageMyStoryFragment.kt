package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentMypageLikestoryBinding

class MyPageMyStoryFragment: Fragment() {
    lateinit var binding: FragmentMypageLikestoryBinding
    private var myPageStoryDatas = ArrayList<MyPageStory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageLikestoryBinding.inflate(inflater,container,false)

        //데이터 리스트 생성 더미데이터
        myPageStoryDatas.apply {
            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex2,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex3,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
        }

        //더미데이터랑 어댑터 연결
        val myPageStoryRVAdapter = MyPageStoryRVAdapter(myPageStoryDatas)
        //리사이클러뷰에 어댑터를 연결
        binding.myPageStoryRecyclerView.adapter = myPageStoryRVAdapter


        val dividerItemDecoration =
            DividerItemDecoration(binding.myPageStoryRecyclerView.context, LinearLayoutManager(activity).orientation)

        binding.myPageStoryRecyclerView.addItemDecoration(dividerItemDecoration)


        return binding.root
    }

}