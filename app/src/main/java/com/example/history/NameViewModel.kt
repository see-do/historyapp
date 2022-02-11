package com.example.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
    private val _currentName : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val currentName : LiveData<String> get() = _currentName
}