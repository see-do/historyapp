package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentAllBinding
import com.example.history.databinding.FragmentOrientalhistoryBinding

class OrientalHistoryFragment : Fragment() {
    lateinit var binding: FragmentOrientalhistoryBinding
    private var storyDatas = ArrayList<Story>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrientalhistoryBinding.inflate(inflater,container,false)

        //데이터 리스트 생성 더미데이터
        storyDatas.apply {
            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex1,12,12))
            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex2,12,12))
            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex3,12,12))
            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex4,12,12))
        }

        //더미데이터랑 어댑터 연결
        val storyRVAdapter = StoryRVAdapter(storyDatas)
        //리사이클러뷰에 어댑터를 연결
        binding.homeStoryRecyclerView.adapter = storyRVAdapter


        return binding.root
    }


}