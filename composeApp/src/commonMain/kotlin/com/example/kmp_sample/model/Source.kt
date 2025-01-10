package com.example.kmp_sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val dataType: String,
    val title: String,
    val uri: String
)