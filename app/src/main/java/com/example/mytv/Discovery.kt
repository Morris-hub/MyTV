package com.example.mytv

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

@Composable
fun RecommendedFilmsScreen() {
    val apiKey = "75838512c16c0a58994621ef0e821d86"
    val coroutineScope = rememberCoroutineScope()
    val filmList = remember { mutableStateListOf<FilmDetails>() }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    val films = getRecommendedFilms(apiKey)
                    filmList.clear()
                    filmList.addAll(films)
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Empfohlene Filme anzeigen")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filmList) { film ->
                FilmListItem(film)
            }
        }
    }
}

@Composable
fun DiscoveryListItem(film: FilmDetail) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Titel: ${film.title}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Beschreibung: ${film.description}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            film.imageUrl?.let { imageUrl ->
                val painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w500$imageUrl",
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.placeholder) // Placeholder image resource, optional
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = "Film Poster",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

data class FilmDetail(
    val title: String,
    val description: String,
    val imageUrl: String?
)

fun getRecommendedFilms(apiKey: String): List<FilmDetails> = runBlocking {
    val client = OkHttpClient()
    val films: MutableList<FilmDetails> = mutableListOf()

    val url = "https://api.themoviedb.org/3/movie/{movie_id}/recommendations?api_key=$apiKey"
    val request = Request.Builder()
        .url(url)
        .build()

    val response = client.newCall(request).execute()
    val responseBody = response.body?.string()

    if (response.isSuccessful && responseBody != null) {
        val json = JSONObject(responseBody)
        val results = json.getJSONArray("results")

        for (i in 0 until results.length()) {
            val filmObject = results.getJSONObject(i)
            val title = filmObject.getString("title")
            val description = filmObject.getString("overview")
            val imageUrl = filmObject.getString("poster_path")
            val film = FilmDetails(title = title, description = description, imageUrl = imageUrl)
            films.add(film)
        }
    }

    films
}/*

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecommendedFilmsScreen()
}
*/