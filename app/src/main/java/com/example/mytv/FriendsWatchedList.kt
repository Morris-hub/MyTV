package com.example.mytv


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text



class FriendsWatchedList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(android.graphics.Color.parseColor("#537FA3")))
            ) {
                TopArrow()
                // Add your content here

                TopBarFriends()
                // Add your content here
                DividerDefaults

                TwoWordsRowWithImage()
                //Add your content here

                TwoWordsSecondRowWithImage()
                //Add your content here

                TwoWordsThirdRowWithImage()
                //Add your content here

                TwoWordsForthRowWithImage()
                //Add your content here
            }
        }
    }
}
@Composable
fun TopArrow() {
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
fun TopBarFriends() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 1.dp, start = 30.dp, end = 1.dp),
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
            text = "GENERS",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier.weight(7f)
        )
        Spacer(modifier = Modifier.width(18.dp))

        Box(modifier = Modifier.weight(1f))
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
        modifier = Modifier.padding(top = 1.dp, start = 270.dp, end = 20.dp)
    )
}
@Composable
fun TwoWordsRowWithImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Shows I watched",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
            color = Color.White,
            modifier = Modifier.weight(2.7f)
        )
        Spacer(modifier = Modifier.width(1.dp))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "sappho",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(1.dp)
            ) {
                val image: Painter = painterResource(id = R.drawable.user_01c)
                Image(
                    painter = image,
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(1.dp))
    Image(
        painter = painterResource(id = R.drawable.group_80),
        contentDescription = "list movies i watched",
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 1.dp),
        contentScale = ContentScale.Crop
    )
}
@Composable
fun TwoWordsSecondRowWithImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Old Hollywood Films",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
            color = Color.White,
            modifier = Modifier.weight(2.7f)
        )
        Spacer(modifier = Modifier.width(1.dp))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "krovora",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(1.dp)
            ) {
                val image: Painter = painterResource(id = R.drawable.user_07a_2_)
                Image(
                    painter = image,
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(1.dp))
    Image(
        painter = painterResource(id = R.drawable.group_82),
        contentDescription = "old hollywood",
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 1.dp),
        contentScale = ContentScale.Crop
    )
}
@Composable
fun TwoWordsThirdRowWithImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "movies/shows I recently watched",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
            color = Color.White,
            modifier = Modifier.weight(2.9f)
        )
        Spacer(modifier = Modifier.width(1.dp))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "ronnie",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(1.dp)
            ) {
                val image: Painter = painterResource(id = R.drawable.group_82_1_)
                Image(
                    painter = image,
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(1.dp))
    Image(
        painter = painterResource(id = R.drawable.group_84),
        contentDescription = "old hollywood",
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 1.dp),
        contentScale = ContentScale.Crop
    )
}
@Composable
fun TwoWordsForthRowWithImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Anime",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
            color = Color.White,
            modifier = Modifier.weight(3.7f)
        )
        Spacer(modifier = Modifier.width(1.dp))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "josh",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 15.sp),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(1.dp)
            ) {
                val image: Painter = painterResource(id = R.drawable.user_02a)
                Image(
                    painter = image,
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(1.dp))
    Image(
        painter = painterResource(id = R.drawable.group_87),
        contentDescription = "old hollywood",
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 1.dp),
        contentScale = ContentScale.Crop
    )
}