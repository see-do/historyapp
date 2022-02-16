package com.example.history

interface DeleteView {
    fun onDeleteLoading()
    fun onDeleteSuccess(response : Boolean)
    fun onDeleteFailure()
}