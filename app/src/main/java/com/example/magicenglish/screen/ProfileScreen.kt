package com.example.magicenglish.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.magicenglish.navigation.QuizHomeNavigation
import com.example.magicenglish.ui.theme.Gold
import com.example.magicenglish.ui.theme.Light


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    profileImage: Uri,
    name: String,
    signOutClicked: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
           Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Card(modifier = Modifier.padding(top=14.dp, start = 10.dp)) {
                        AsyncImage(
                            model = profileImage,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top=25.dp),
                        text = name,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Button(
                        border = BorderStroke(width = 1.5.dp, color = Light),
                        colors = ButtonDefaults.buttonColors(containerColor = Gold),
                        modifier = Modifier.padding(top=15.dp, end = 10.dp),
                        onClick = {
                            signOutClicked()
                        }
                    ) {
                        Text(text = "Sign out", color = Color.Black,)
                    }
                }
            }
        },
        content = {
            Column(modifier = Modifier.padding(top=60.dp)) {
               QuizHomeNavigation()
            }
        }
    )
}

