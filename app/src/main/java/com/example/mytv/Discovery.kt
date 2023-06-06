package com.example.tmdbapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mytv.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class Discovery : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecommendedMoviesScreen()
        }
    }
}

@Composable
fun RecommendedMoviesScreen() {
    val filmList = remember { mutableStateListOf<FilmDetails>() }
    val apiKey = "75838512c16c0a58994621ef0e821d86" // Replace with your TMDB API key
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val recommendedMovies = getRecommendedMovies(apiKey)
            filmList.addAll(recommendedMovies)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(filmList) { film ->
            FilmGridItem(film)
        }
    }
}

@Composable
fun FilmGridItem(film: FilmDetails) {
    Card(
        modifier = Modifier
            // .padding(8.dp)
            //.fillMaxWidth()
            .height(200.dp),
        //elevation = 4.dp
    ) {
        Column(
            //modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            film.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500$imageUrl",
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.placeholder) // Placeholder image resource, optional
                        }
                    ),
                    contentDescription = "Film Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        //.aspectRatio(0.7f)
                        .fillMaxHeight()
                )
            }
            Text(
                text = film.title,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

data class FilmDetails(
    val title: String,
    val imageUrl: String?
)

fun getRecommendedMovies(apiKey: String): List<FilmDetails> {
    val client = OkHttpClient()
    val films: MutableList<FilmDetails> = mutableListOf()

    val url = "https://api.themoviedb.org/3/discover/movie?api_key=$apiKey&sort_by=popularity.desc"
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
            val imageUrl = filmObject.getString("poster_path")
            val film = FilmDetails(title = title, imageUrl = imageUrl)
            films.add(film)
        }
    }

    return films
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecommendedMoviesScreen()
}
