package com.example.mendofeelkotlin.ModelClass

import com.example.mendofeelkotlin.ModelClass.Article

data class MyDataClass(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)