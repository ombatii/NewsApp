package com.example.kmp_sample.domain

import com.example.kmp_sample.model.AllArticles
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchArticles(): Flow<AllArticles>?
}