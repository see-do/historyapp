package com.example.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentStoryDetailBinding


class StoryDetailFragment : Fragment() {
    lateinit var binding : FragmentStoryDetailBinding
    private var hashtagList = arrayListOf<Hashtag>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        hashtagList.apply{
            add(Hashtag("dad"))
            add(Hashtag("dad"))
            add(Hashtag("dad"))
            add(Hashtag("dad"))
            add(Hashtag("dad"))
        }

        binding.storyHashtagRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.storyHashtagRv.adapter = HashtagRVAdapter(hashtagList, 1)
        return binding.root
    }
}