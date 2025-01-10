package com.example.kmp_sample.di

import com.example.kmp_sample.data.NewsRepositoryImpl
import com.example.kmp_sample.domain.NewsRepository
import com.example.kmp_sample.presentation.NewsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClient {
            provideHttpClient()
        }
    }
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }


    viewModel {
        NewsViewModel(get())
    }
}


fun provideHttpClient(): HttpClient {
    return HttpClient {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            register(
                ContentType.Application.Json, KotlinxSerializationConverter(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            )
            json(
                Json {
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Accept, ContentType.Application.Json)
            url {
                parameters.append("apiKey", "1bbddd30-f471-45f6-8dc8-d25577f6e591")
                parameters.append("lang", "eng")
            }
        }
    }
}