package com.example.history

interface CommentView {
    fun onCommentLoading()
    fun onCommentSuccess(status : String, body : List<Comment?>)
    fun onCommentFailure()
}