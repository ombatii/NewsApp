package com.example.kmp_sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Articles(
    val count: Int,
    val page: Int,
    val pages: Int,
    val results: List<Result>,
    val totalResults: Int
)