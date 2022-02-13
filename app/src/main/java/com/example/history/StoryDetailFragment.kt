package com.example.history

import android.app.AlertDialog
import android.app.AppComponentFactory
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.history.databinding.FragmentStoryDetailBinding


class StoryDetailFragment(story : OneStory) : Fragment() {
    lateinit var binding : FragmentStoryDetailBinding
    private var hashtagList = arrayListOf<String>()
    private var commentList = arrayListOf<String>()
    var story = story
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
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
        Glide.with(requireContext()).load(if(story.images.isNullOrEmpty()){
            "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
        } else {
            story.images!![0].imageUrl
        }).into(binding.storyImageIv)
        binding.storyContentTv.text = story.contents
        binding.storyLikeTv.text = story.totalLike.toString()
        binding.storyCommentTv.text = story.totalComment.toString()


        binding.storyCommentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.storyCommentRv.adapter = CommentRVAdapter(commentList)

        binding.storySettingLo.setOnClickListener {
            val storyService = StoryService()
            val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
            val token = spf?.getString("accessToken",null)
            Log.d("dele","$token")
            storyService.deleteStory(token!!,1)
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
        val addressList = "gyeondeo@gmail.com"
        val intent = Intent(Intent.ACTION_SEND, Uri.fromParts("mailto", "example@gasd.com", null)).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, addressList)
            putExtra(Intent.EXTRA_TEXT, "신고 게시글 제목:\n사유:")
        }
            //Uri.parse("mailto:")

        startActivity(Intent.createChooser(intent,"메일 전송하기"))

    }
}