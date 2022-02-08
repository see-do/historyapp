package com.example.history

interface AuthView {
    fun onAuthLoading()
    fun onAuthSuccess(body : Boolean)
    fun onAuthFailure()
}