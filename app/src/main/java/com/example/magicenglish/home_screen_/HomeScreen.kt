package com.example.magicenglish.home_screen_

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.magicenglish.R
import com.example.magicenglish.ui.theme.DarkBlue
import com.example.magicenglish.ui.theme.LightSalmon
import com.example.magicenglish.ui.theme.Moccasin
import com.example.magicenglish.ui.theme.PaleGoldenrod


@Composable
fun HomeScreen(navController: NavController,onClick:()->Unit)
{
    Column(
        modifier = Modifier
//            .fillMaxHeight()
            .padding(20.dp),
    ) {
        Divider(color = DarkBlue, modifier = Modifier.padding(top=10.dp), thickness = 1.5.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(120.dp)
                    .clickable {

                    },
                colors = CardDefaults.cardColors(
                    containerColor = Moccasin,
                )
            ) {
                Row {
                    Text(
                        text = "Test Trainer", fontSize = 25.sp,
                        modifier = Modifier
                            .padding(vertical = 26.dp, horizontal = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.grammar_trainer),
                        contentDescription = "test"
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(120.dp)
                    .clickable {
                        onClick()
                    },
                colors = CardDefaults.cardColors(containerColor = PaleGoldenrod)
            ) {
                Row {
                    Text(
                        text = "Dictionary",
                        fontSize = 25.sp,
                        modifier = Modifier
                            .padding(vertical = 26.dp, horizontal = 10.dp)
                    )
                    Image(painter = painterResource(id = R.drawable.dictionary), contentDescription = "vocabulary")
                }
            }
        }
    }
}
