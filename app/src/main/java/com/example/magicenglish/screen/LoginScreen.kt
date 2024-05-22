package com.example.magicenglish.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.magicenglish.R
import com.example.magicenglish.ui.theme.DarkBlue
import com.example.magicenglish.ui.theme.MediumBlue
import com.example.magicenglish.ui.theme.Orange

@SuppressLint("UnrememberedMutableState")
@Composable
fun LogInScreen(
    onSignInClick: () -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.magic),
            contentDescription = "login",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(top=45.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 60.sp,
                    color = DarkBlue,
                    fontWeight = FontWeight.Bold,
                    text = "Magic English",
                    fontFamily = FontFamily.Cursive
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = DarkBlue,
                    fontWeight = FontWeight.Medium,
                    text = "Mastery begins with learning English.",
                    lineHeight = 35.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.padding(top=25.dp)) {
                Card(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .height(55.dp)
                        .fillMaxWidth()
                        .clickable {
                            onSignInClick()
                        },
                    shape = RoundedCornerShape(40.dp),
                    border = BorderStroke(width = 2.dp, color = Orange),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(32.dp)
                                .padding(0.dp)
                                .align(Alignment.CenterVertically),
                            painter = painterResource(id = R.drawable.ic_google_logo),
                            contentDescription = "google_logo"
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .align(Alignment.CenterVertically),
                            text = "Sign In With Google",
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}