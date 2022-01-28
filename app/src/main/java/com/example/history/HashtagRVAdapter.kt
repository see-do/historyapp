package com.example.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemHashtagBinding



class HashtagRVAdapter(private val hashtagList : ArrayList<Hashtag>) : RecyclerView.Adapter<HashtagRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHashtagBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(hashtagList[position])
        holder.binding.itemHashtagTv.setOnClickListener {
            removeHashtag(position)
        }
    //holder.bind(hashtagList[position])
    }

    override fun getItemCount(): Int = hashtagList.size
    inner class ViewHolder(val binding : ItemHashtagBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hashtag : Hashtag){
            binding.itemHashtagTv.text = hashtag.hashtag
        }
    }
    fun removeHashtag(position: Int){
        hashtagList.removeAt(position)
        notifyDataSetChanged()
    }

}
