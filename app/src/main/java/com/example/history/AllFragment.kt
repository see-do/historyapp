package com.example.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.history.databinding.FragmentAllBinding

class AllFragment: Fragment(), StoryView {
    lateinit var binding: FragmentAllBinding
    private var storyDatas = ArrayList<Story>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater,container,false)
        initView()

        val spf = requireContext().getSharedPreferences("story", AppCompatActivity.MODE_PRIVATE)
        val token = spf.edit()
//        token.clear()
//        token.commit()
        var title = spf.getString("title",null)
        Log.d("title","$title")
        //if(title != null){

        //}
        //데이터 리스트 생성 더미데이터
//        storyDatas.apply {
//            add(Story("클레오파트라는 정말 흑인이었을까?",R.drawable.story_cover_img_ex1,12,12))
//            add(Story("피라미드는 원래 흰색이었다?",R.drawable.story_cover_img_ex2,12,12))
//            add(Story("영화 '메시지'에 담긴 숨겨진 의미",R.drawable.story_cover_img_ex3,12,12))
//            add(Story("바벨탑은 실존했다?",R.drawable.story_cover_img_ex4,12,12))
//        }

        //더미데이터랑 어댑터 연결




       return binding.root
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
                Story(story.title,
                    if(story.images.isNullOrEmpty()){
                        "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
                    } else {
                        story.images[0].imageUrl
                    },story.totalLike,story.totalComment)
            )
            Log.d("testt","${storyDatas}")
        }

        var storyRVAdapter = StoryRVAdapter(storyDatas)
        //리사이클러뷰에 어댑터를 연결
        storyRVAdapter.myItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(story: Story) {
                changeFragment(story)
            }
        })
        binding.homeStoryRecyclerView.adapter = storyRVAdapter
    }

    fun changeFragment(story: Story) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, StoryDetailFragment(story)).commitAllowingStateLoss()
    }

    private fun initView(){
        val storyService = StoryService()
        storyService.setStoryView(this)
        storyService.getStoriesAllOrderByRecent()
    }

}