package com.example.mytv

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class Settingpage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySettingsPage()
        }
    }
}

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySettingsPage() {
    val nameSwitchState = remember { mutableStateOf(false) }
    val ageSwitchState = remember { mutableStateOf(false) }
    val darkModeSwitchState = remember { mutableStateOf(false) }
    val showMessage = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (darkModeSwitchState.value) Color.Black else Color(0xFF537FA3))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Settings",
                fontSize = 24.sp,
                color = if (darkModeSwitchState.value) Color.White else Color.White,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.Start)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .fillMaxWidth()
                    .clickable { /* Handle profile picture click */ }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(90.dp)
                        .padding(bottom = 1.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user_01cc),
                        contentDescription = "Profile Picture"
                    )
                }
                Text(
                    text = "Edit Profile",
                    fontSize = 15.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    modifier = Modifier.padding(bottom = 1.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Activity",
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Notifications",
                    modifier = Modifier.weight(2f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Switch(
                    checked = nameSwitchState.value,
                    onCheckedChange = { nameSwitchState.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = if (darkModeSwitchState.value) Color.White else Color(0xFF537FA3),
                        checkedTrackColor = if (darkModeSwitchState.value) Color(0xFF537FA3) else Color(
                            0xFFFFFFFF
                        )
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Private Account",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Switch(
                    checked = ageSwitchState.value,
                    onCheckedChange = { ageSwitchState.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = if (darkModeSwitchState.value) Color.White else Color(0xFF537FA3),
                        checkedTrackColor = if (darkModeSwitchState.value) Color(0xFF537FA3) else Color(0xFFFFFFFF)
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Dark Mode",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Switch(
                    checked = darkModeSwitchState.value,
                    onCheckedChange = { darkModeSwitchState.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF537FA3)
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Change Password",
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .weight(1f),
                    fontSize = 23.sp,
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Security & Privacy",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Languages",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "About Us",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "FAQs",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Log Out",
                    modifier = Modifier.weight(1f),
                    fontSize = 23.sp, // Increased font size
                    color = if (darkModeSwitchState.value) Color.White else Color.White
                )
                Text(
                    text = "\u003E", // Open arrow symbol
                    color = if (darkModeSwitchState.value) Color.White else Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { saveSettings { showMessage.value = it } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Save",
                    fontSize = 18.sp, // Increased font size
                    color = Color.White
                )
            }
        }

        if (showMessage.value) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    Button(onClick = { showMessage.value = false }) {
                        Text(text = "Dismiss")
                    }
                }
            ) {
                Text(text = "Settings saved")
            }
        }
    }
}

fun saveSettings(callback: (Boolean) -> Unit) {
    callback(true)
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsPage() {
    MySettingsPage()
}
