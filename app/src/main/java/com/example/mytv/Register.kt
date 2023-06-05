
package com.example.mytv

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytv.ui.theme.PurpleGrey40
import kotlinx.coroutines.launch

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    suspend fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    navigateToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkBoxTwo by remember { mutableStateOf(false) }


    val coroutineScope = rememberCoroutineScope()
    val firebaseAuthService = FirebaseAuthService()



    var isPasswordVisible by remember { mutableStateOf(false) }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#537FA3")))
    ) {
        Image(
            painter = painterResource(id = R.drawable.mytvlogo),
            contentDescription = "Logo App",
            modifier = Modifier
                .padding(top = 40.dp)
                .size(50.dp),
        )

        Text(
            text = "MyTV",
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(top = 0.dp)
        )

        Text(
            text = "Create your Account",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
        )

        TextField(
            value = username,
            onValueChange = { username = it },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color(android.graphics.Color.parseColor("#537FA3")))
                    )
                }
            },
            placeholder = {
                Text(text = "Username", color = PurpleGrey40)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color(android.graphics.Color.parseColor("#537FA3")))
                    )
                }
            },
            placeholder = {
                Text(text = "Email", color = PurpleGrey40)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color(android.graphics.Color.parseColor("#537FA3")))
                    )
                }
            },

            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )

                }

            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            placeholder = {
                Text(text = "Password", color = PurpleGrey40)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        )

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkBoxTwo,
                onCheckedChange = { checkBoxTwo = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Red,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Agree with Terms and Conditions",
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontSize = 12.sp
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        firebaseAuthService.registerUser(email, password)
                        navigateToLogin()
                    } catch (e: Exception) {
                        // Handle registration error
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF314C61)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp
            ),
            shape = CircleShape
        ) {
            Text(
                text = "Sign Up with Email",
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        TextButton(
            onClick = { navigateToLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            Text(
                text = "Already have an account? " +
                        "Sign In",
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )


        }
    }
}

