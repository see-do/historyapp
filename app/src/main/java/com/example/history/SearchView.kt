package com.example.history

interface SearchView {
    fun onSearchLoading()
    fun onSearchSuccess(body : List<Body>?)
    fun onSearchFailure()
}