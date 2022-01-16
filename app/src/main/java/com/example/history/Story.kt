package com.example.history

data class Story(
    var title: String? = "",
    var coverImg: Int?=null,
    var likeNumber: Int?=null,
    var commentNumber: Int?=null,
    var detail: ArrayList<String>?=null

)
