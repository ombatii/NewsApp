package com.example.kmp_sample.data

import com.example.kmp_sample.domain.NewsRepository
import com.example.kmp_sample.model.AllArticles
import com.example.kmp_sample.model.Articles
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val BASE_URL = "http://eventregistry.org/api/"
class NewsRepositoryImpl(private val client: HttpClient) : NewsRepository {

    override suspend fun fetchArticles(): Flow<AllArticles>? = flow {
        try {
            val response: HttpResponse = client.post("${BASE_URL}v1/article/getArticles"){
                parameter("apiKey","1bbddd30-f471-45f6-8dc8-d25577f6e591")
                parameter("lang", "eng")

            }

            println(response.bodyAsText())
            if (response.status.value in 200..299) {
                val articles: AllArticles = response.body()
                emit(articles)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}