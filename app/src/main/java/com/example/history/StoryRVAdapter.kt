package com.example.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemStoryBinding

class StoryRVAdapter(private val storyList:ArrayList<Story>) :RecyclerView.Adapter<StoryRVAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storyList[position])

    }

    override fun getItemCount(): Int = storyList.size


    //뷰홀더

    inner class ViewHolder(val binding:ItemStoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(story:Story){
            binding.itemStoryTitleTv.text=story.title
            binding.itemStoryLikeTv.text= story.likeNumber.toString()
            binding.itemStoryCommentTv.text=story.commentNumber.toString()
            binding.itemStoryCoverImgIv.setImageResource(story.coverImg!!)
        }
    }

}