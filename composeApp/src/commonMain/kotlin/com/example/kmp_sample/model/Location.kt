package com.example.kmp_sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val country: Country,
    val label: Label,
    val type: String
)