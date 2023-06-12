package com.example.mytv

//import android.app.DownloadManager.Request
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import okhttp3.Request
import okhttp3.OkHttpClient



data class MyTv(
    var filmName: String,
    var filmList: String,
    val description: String,
    val imageUrl: String?
)

class MyTvViewModel : ViewModel() {
    private val myTv = MutableStateFlow(
        MyTv(
            "Avatar",
            "Blockbuster",
            "Papyrus",
            "https://www.gravatar.com/avatar/HASH"
        )
    )

    val myTvLiveData: LiveData<MyTv> = myTv.asLiveData()


    fun searchFilms(apiKey: String, filmName: String): List<FilmDetails> {
        val client = ""
        val films: MutableList<FilmDetails> = mutableListOf()

        val url = "https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$filmName"
        val request = Request.Builder()
            .url(url)
            .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body?.string()

        if (response.isSuccessful && responseBody != null) {
            val json = JSONObject(responseBody.orEmpty())
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
}

private fun <T> MutableStateFlow<T>.asLiveData(): LiveData<T> {
    TODO("Not yet implemented")
}


