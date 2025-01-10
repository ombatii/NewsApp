package com.example.kmp_sample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kmp_sample.model.Result
import com.example.kmp_sample.presentation.NewsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController)
            }
            composable("details/{articleId}") { backStackEntry ->
                val articleId = backStackEntry.arguments?.getString("articleId")
                DetailsScreen(articleId)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = koinViewModel<NewsViewModel>()
    val articles = viewModel.articles.value

    Column {
        Text("News Articles", style = MaterialTheme.typography.h5, modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(articles) { article ->
                ArticleItem(article) { selectedArticle ->
                    navController.navigate("details/${selectedArticle.uri}")
                }
            }
        }
    }
}

@Composable
fun ArticleItem(article: Result, onClick: (Result) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(article) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = article.body.take(100) + "...", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun DetailsScreen(articleId: String?) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Article Details", style = MaterialTheme.typography.h5)
        Text("Article ID: ", style = MaterialTheme.typography.body1)
    }
}
