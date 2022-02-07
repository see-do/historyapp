package com.example.history

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentStoryDetailBinding


class StoryDetailFragment(story : Story) : Fragment() {
    lateinit var binding : FragmentStoryDetailBinding
    private var hashtagList = arrayListOf<Hashtag>()
    private var commentList = arrayListOf<String>()
     var story = story
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        hashtagList.apply{
            add(Hashtag("#dad1"))
            add(Hashtag("#dad2"))
            add(Hashtag("#dad3"))
            add(Hashtag("#dad4"))
            add(Hashtag("#dad5"))
        }

        binding.storyHashtagRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.storyHashtagRv.adapter = HashtagRVAdapter(hashtagList, 1)
        commentList.apply{
            add("#dasd1")
            add("#dasd2")
            add("#dasd3")
            add("#dasd4")
            add("#dasd5")
        }
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_report, null)
        builder.setView(dialogView)

        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)

        builder.setView(dialogView)
        binding.storyTitleTv.text = story.title
        binding.storyCommentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.storyCommentRv.adapter = CommentRVAdapter(commentList)
        binding.storySettingIv.setOnClickListener {
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.dialog_report_tv).setOnClickListener {
                report()
            }
        }

        binding.storyExitIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, HomeFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }
    private fun report(){
        val intent = Intent(Intent.ACTION_SEND).apply{
            type = "text/plain"
            Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, "gyeondeo@gmail.com")
            putExtra(Intent.EXTRA_TEXT,"신고 게시글 제목, 사유를 적어주세요")
        }
        startActivity(Intent.createChooser(intent,"메일 전송하기"))

    }
}