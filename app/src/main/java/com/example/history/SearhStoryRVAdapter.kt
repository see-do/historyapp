package com.example.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.history.databinding.ItemMypageStoryBinding
import com.example.history.databinding.ItemStoryBinding

class SearchStoryRVAdapter (private val myPageStoryList:ArrayList<Body>) : RecyclerView.Adapter<SearchStoryRVAdapter.ViewHolder>(){

    lateinit var context:Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMypageStoryBinding = ItemMypageStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myPageStoryList[position])

    }

    override fun getItemCount(): Int = myPageStoryList.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun addStories()
    //뷰홀더

    inner class ViewHolder(val binding: ItemMypageStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(searchStory:Body){
            binding.itemMyPageStoryTitleTv.text=searchStory.title
            binding.itemMyPageStoryDetailTv.text=searchStory.contents
            binding.itemMyPageStoryNickNameTv.text=if(searchStory.user == null){
                "duck"
            } else {
                searchStory.user.nickName
            }
            binding.itemMyPageStoryLikeTv.text= searchStory.totalLike.toString()
            binding.itemMyPageStoryCommentTv.text=searchStory.totalComment.toString()
            Glide.with(context)
                .load(if(searchStory.user == null){
                    "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
                }else{
                    searchStory.user.profileImgUrl
                })
                .into(binding.itemMyPageStoryProfileImgIv)
        }
    }

}