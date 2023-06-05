package com.example.mytv


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MovieProfile : ComponentActivity() {
    val BackgroundColor = Color(android.graphics.Color.parseColor("#537FA3"))
    //val BackgroundColor = Color(#537FA3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieInfoScreen()
        }
    }
}

@Composable
fun MovieInfoScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top

    ) {
        //Background Image
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.everything),
                contentDescription = "Landscape",
                contentScale = ContentScale.Crop
            )
            //Gradient
            Column(modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(android.graphics.Color.parseColor("#537FA3"))
                        )
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(0.6f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.33f),
                            painter = painterResource(id = R.drawable.cover),
                            contentDescription = "Cover",
                            contentScale = ContentScale.Fit //keeep image size

                        )
                        //Titel Text
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "Everything Everywhere All at Once",
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "2022 - 139 min", fontSize = 20.sp, color = Color.White)
                            Text(
                                text = "Trailer".uppercase(),
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor("#537FA3")))
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(width = 1.dp, Color.White))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(50.dp, 30.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Rate", fontSize = 20.sp, color = Color.White)
                        Text(text = "Save", fontSize = 20.sp, color = Color.White)
                        Text(text = "Share", fontSize = 20.sp, color = Color.White)
                    }
                }

                // BESCHREIBUNG
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(text = "Synposis", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "" +
                            "When an interdimensional rupture unravels reality, an unlikely hero must channel her newfound powers to fight bizarre and bewildering dangers from the multiverse as the fate of the world hangs in the balance.", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Normal)
                }
                //WATCHED BY
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(text = "Watched by", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Medium)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Card(modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                            .padding(0.dp, 10.dp, 10.dp),
                            shape = RoundedCornerShape(50.dp)
                        ) {}
                        Card(modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                            .padding(0.dp, 10.dp, 10.dp),
                            shape = RoundedCornerShape(50.dp)
                        ) {}
                        Card(modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                            .padding(0.dp, 10.dp, 10.dp),
                            shape = RoundedCornerShape(50.dp)
                        ) {}
                    }

                }
            }
        }
    }

}

@Composable
fun BackgroundImage() {

}

@Composable
fun ImageResourceDemo() {
    val image: Painter = painterResource(id = R.drawable.demomovie)
    Image(
        painter = image,
        contentDescription = "",
        modifier = Modifier.size(200.dp) // Ändere die Größe auf 200dp
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}