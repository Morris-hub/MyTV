package com.example.mytv

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.tooling.preview.Preview

class FavoriteList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(color = Color(0xFF537FA3)) {
            content()
        }
    }
}

@Composable
fun MyScreenContent() {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Favorites",
                modifier = Modifier.padding(2.dp),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start
            )

            Image(
                painter = painterResource(R.drawable.delete_1),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
                    .padding(2.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.placeholder_image10),
                contentDescription = null,
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(R.drawable.placeholder_image10),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp),

                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(R.drawable.placeholder_image10),
                contentDescription = null,
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteListPreview() {
    MyApp {
        MyScreenContent()
    }
}
