package com.example.kmp_sample.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Concept(
    @SerialName("#META ITEM")
    val metaItem: String,
    val label: Label,
    val location: Location,
    val score: Int,
    val type: String,
    val uri: String
)