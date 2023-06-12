package com.example.mytv

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapi.RecommendedMoviesScreen
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        setContent {
            ComposeView(this@MainActivity).apply {
                setContent {
                    MyComposable(viewModel)
                }
            }
        }
    }
}

@Composable
fun MyComposable(viewModel: ViewModel) {
    RecommendedMoviesScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecommendedMoviesScreen()
}
