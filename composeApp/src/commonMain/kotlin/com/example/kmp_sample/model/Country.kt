package com.example.kmp_sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val label: Label,
    val type: String
)