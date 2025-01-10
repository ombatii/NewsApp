package com.example.kmp_sample.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("#META ITEM")
    val metaItem: String,
    val authors: List<Author>,
    val body: String,
    val concepts: List<Concept>,
    val dataType: String,
    val date: String,
    val dateTime: String,
    val dateTimePub: String,
    val eventUri: String?,
    val image: String,
    val isDuplicate: Boolean,
    val lang: String,
    val relevance: Int,
    val sentiment: Double,
    val shares: Shares,
    val sim: Int,
    val source: Source,
    val time: String,
    val title: String,
    val uri: String,
    val url: String,
    val wgt: Int
)

@Serializable
data class Author(
    val uri: String,
    val name: String,
    val type: String,
    val isAgency: Boolean
)