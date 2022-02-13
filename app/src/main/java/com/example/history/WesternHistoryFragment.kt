package com.example.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentAllBinding
import com.example.history.databinding.FragmentWesternhistoryBinding

class WesternHistoryFragment: Fragment(), StoryView {
    lateinit var binding: FragmentWesternhistoryBinding
    private var storyDatas = ArrayList<OneStory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWesternhistoryBinding.inflate(inflater,container,false)
        initView()
        Log.d("western_recycler","rer")

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
            Log.d("western_recycler","${storyDatas}")
        }
        binding.westernStoryRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        var storyRVAdapter = StoryRVAdapter(storyDatas)
        //리사이클러뷰에 어댑터를 연결
        storyRVAdapter.myItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(story: OneStory) {
                changeFragment(story)
            }
        })
        binding.westernStoryRecyclerView.adapter = storyRVAdapter

    }

    private fun initView(){
        val storyService = StoryService()
        storyService.setStoryView(this)
        storyService.getStoriesCategoryOrderByRecent("WESTERN")
        Log.d("western_recyv","dasd")
    }
}