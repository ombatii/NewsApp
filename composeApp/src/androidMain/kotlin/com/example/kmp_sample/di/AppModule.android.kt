package com.example.kmp_sample.di

import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    //single { HttpClient(Android) }
}