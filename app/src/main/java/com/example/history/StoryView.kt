package com.example.history

interface StoryView {
    fun onStoryLoading()
    fun onStorySuccess(status : String, body : List<OneStory>)
    fun onStoryFailure()
}