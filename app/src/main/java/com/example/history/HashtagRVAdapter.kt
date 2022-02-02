package com.example.history

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemHashtagBinding



class HashtagRVAdapter(private val hashtagList : ArrayList<Hashtag>, private val code : Int) : RecyclerView.Adapter<HashtagRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHashtagBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.change(code)
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
        fun change(code : Int){
            if (code == 1){
                binding.itemHashtagIv.setImageResource(R.drawable.button_hashtag_detail)
                binding.itemHashtagTv.setTextColor(Color.parseColor("#27316A"))
            }
        }
    }
    fun removeHashtag(position: Int){
        hashtagList.removeAt(position)
        notifyDataSetChanged()
    }

}
