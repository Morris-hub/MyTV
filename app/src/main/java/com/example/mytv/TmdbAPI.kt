package com.example.mytv

//import android.app.DownloadManager.Request
import androidx.compose.runtime.R
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import coil.compose.rememberImagePainter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.offline.DownloadManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class TmdbAPI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmSearchScreen()
                }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmSearchScreen() {
    var filmName by remember { mutableStateOf("") }
    var filmList by remember { mutableStateOf<List<FilmDetails>>(emptyList()) }
    val apiKey = "75838512c16c0a58994621ef0e821d86"
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = filmName,
            onValueChange = { filmName = it },
            label = { Text("Filmname eingeben") },
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
            )
        )

        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                   // val films = searchFilms(apiKey, filmName)
                    //filmList = films
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Suchen")
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
fun FilmListItem(film: FilmDetails) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Titel: ${film.title}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Beschreibung: ${film.description}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            film.imageUrl?.let { imageUrl ->
                val painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w500$imageUrl",
                    builder = {
                        crossfade(true)
                       // placeholder(R.drawable.placeholder) // Placeholder image resource, optional
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

data class FilmDetails(
    val title: String,
    val description: String,
    val imageUrl: String?
)

fun searchFilms(apiKey: String, filmName: String): List<FilmDetails> {
    val client = OkHttpClient()
    val films: MutableList<FilmDetails> = mutableListOf()

    val url = "https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$filmName"
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

    return films
}
