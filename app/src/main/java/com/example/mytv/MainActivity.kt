package com.example.mytv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(android.graphics.Color.parseColor("#537FA3")))

            ) {
                TopLeftArrow()

                TopBar()

                AccountImage()

                WatchedMoviesList()


                }
                // Add your content here
            }
        }
    }

@Composable
fun TopLeftArrow() {
    Box(
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null
        )
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 1.dp, start = 25.dp, end = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "WATCHED",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier.weight(10f)
        )
        Spacer(modifier = Modifier.width(18.dp))

        Text(
            text = "GENRES",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier.weight(7f)
        )
        Spacer(modifier = Modifier.width(18.dp))


        Text(
            text = "FRIENDS",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier.padding(start = 6.dp, end = 14.dp, top = 6.dp, bottom = 6.dp)
        )
        Spacer(modifier = Modifier.width(18.dp))
    }
    Divider(
        color = Color.White,
        thickness = 2.dp,
        modifier = Modifier.padding(top = 1.dp, start = 20.dp, end = 270.dp)


    )
}

@Composable
fun AccountImage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Example Image",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 8.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Annie",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
            color = Color.White,

        )
    }
}




@Composable
fun WatchedMoviesList() {
    Image(
        painter = painterResource(id = R.drawable.movies),
        contentDescription = "My Image",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth()
    )
}