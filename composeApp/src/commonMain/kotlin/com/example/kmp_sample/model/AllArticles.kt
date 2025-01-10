package com.example.kmp_sample.model

import kotlinx.serialization.Serializable

@Serializable
data class AllArticles(
    val articles: Articles
)