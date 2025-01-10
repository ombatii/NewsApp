package com.example.kmp_sample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmp_sample.domain.NewsRepository
import com.example.kmp_sample.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private var _articles: MutableStateFlow<List<Result>> =
        MutableStateFlow(value = emptyList())

    val articles = _articles.asStateFlow()

    init {
        getAllArticles() //
    }

    private fun getAllArticles() {
        viewModelScope.launch {
            val allArticles = newsRepository.fetchArticles()
            allArticles?.collectLatest {  articles ->
                _articles.value = articles.articles.results
                println(articles)
            }
        }
    }

}