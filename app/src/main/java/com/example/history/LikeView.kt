package com.example.history

interface LikeView {
    fun onLikeLoading()
    fun onLikeSuccess(body : Boolean)
    fun onLikeFailure()
}