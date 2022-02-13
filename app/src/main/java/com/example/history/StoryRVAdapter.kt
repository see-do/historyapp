package com.example.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.history.databinding.ItemStoryBinding

class StoryRVAdapter(private val storyList:ArrayList<Story>) :RecyclerView.Adapter<StoryRVAdapter.ViewHolder>(){

    lateinit var context:Context
    interface MyItemClickListener{
        fun onItemClick(story : Story)
    }
    private lateinit var mItemClickListener : MyItemClickListener
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    fun myItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storyList[position])
        holder.binding.itemStoryLo.setOnClickListener {
            mItemClickListener.onItemClick(storyList[position])
        }

    }

    override fun getItemCount(): Int = storyList.size


    //뷰홀더

    inner class ViewHolder(val binding:ItemStoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(story:Story){
            binding.itemStoryTitleTv.text=story.title
            binding.itemStoryLikeTv.text= story.likeNumber.toString()
            binding.itemStoryCommentTv.text=story.commentNumber.toString()
            Glide.with(context)
                .load(story.coverImg)
                .into(binding.itemStoryCoverImgIv)

        }
    }

}