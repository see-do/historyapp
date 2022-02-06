package com.example.history

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoryService {
    fun getStory() {
        val retrofit = Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        //storyService.getStory().
    }

    fun writeStory(){
        val retrofit = Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        //storyService.writeStory()
    }
}