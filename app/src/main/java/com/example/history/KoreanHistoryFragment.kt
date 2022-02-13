package com.example.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentKoreanhistoryBinding

class KoreanHistoryFragment: Fragment(), StoryView {
    lateinit var binding: FragmentKoreanhistoryBinding
    private var storyDatas = ArrayList<OneStory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKoreanhistoryBinding.inflate(inflater,container,false)
        initView()
        //데이터 리스트 생성 더미데이터
//        storyDatas.apply {
//            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex1,12,12))
//            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex2,12,12))
//            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex3,12,12))
//            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex4,12,12))
//        }




        return binding.root
    }

    fun changeFragment(story: OneStory) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, StoryDetailFragment(story)).commitAllowingStateLoss()
    }

    override fun onStoryFailure() {
        TODO("Not yet implemented")
    }

    override fun onStoryLoading() {
        TODO("Not yet implemented")
    }

    override fun onStorySuccess(status: String, body: List<OneStory>) {
        for (story in body){
            storyDatas.add(
                story
            )
            Log.d("korean_recycler","${storyDatas}")
        }

        //더미데이터랑 어댑터 연결
        val storyRVAdapter = StoryRVAdapter(storyDatas)
        //리사이클러뷰에 어댑터를 연결
        storyRVAdapter.myItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(story: OneStory) {
                changeFragment(story)
            }
        })
        binding.homeStoryRecyclerView.adapter = storyRVAdapter

    }

    private fun initView(){
        val storyService = StoryService()
        storyService.setStoryView(this)
        storyService.getStoriesCategoryOrderByRecent("KOREAN")
        Log.d("korean_recyv","dasd")
    }
}