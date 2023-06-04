package com.example.mytv

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPageContent()
        }
    }
}

@Composable
fun MyPageContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#537FA3")))
    ) {
        TopBar()

        AccountImage()

        WatchedMoviesList()

        BottomNavigation()
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
            style = MaterialTheme.typography.h6.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier.weight(10f)
        )
        Spacer(modifier = Modifier.width(18.dp))

        Text(
            text = "GENRES",
            style = MaterialTheme.typography.h6.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier.weight(7f)
        )
        Spacer(modifier = Modifier.width(18.dp))

        Text(
            text = "FRIENDS",
            style = MaterialTheme.typography.h6.copy(fontSize = 20.sp),
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
            style = MaterialTheme.typography.h6.copy(fontSize = 20.sp),
            color = Color.White
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomNavigation() {
    var selectedItem by mutableIntStateOf(0)

    BottomNavigation(
        backgroundColor = Color(0xFF426582),
        modifier = Modifier.padding(top = 8.dp)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.terms_and_conditions),
                    contentDescription = "Discovery"
                )
            },
            label = { Text(text = "Discover") },
            selected = selectedItem == 0,
            onClick = {  }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.lightning_bolt),
                    contentDescription = "Activity"
                )
            },
            label = { Text(text = "Activity") },
            selected = selectedItem == 1,
            onClick = {  }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search"
                )
            },
            label = { Text(text = "Search") },
            selected = selectedItem == 2,
            onClick = {  }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.airdrop),
                    contentDescription = "Nearby"
                )
            },
            label = { Text(text = "Nearby") },
            selected = selectedItem == 3,
            onClick = {  }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.customer),
                    contentDescription = "Profile"
                )
            },
            label = { Text(text = "Profile") },
            selected = selectedItem == 4,
            onClick = { }
        )
    }
}
