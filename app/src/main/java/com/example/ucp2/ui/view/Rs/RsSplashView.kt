package com.example.ucp2.ui.view.Rs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun RsSplashView(
    onMulaiButton: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.background
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Sejahtera\nHospital",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 60.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .background(color = colorResource(id = R.color.background))
        )
        Text(
            text = "Your Health\nOur Commitment",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                onMulaiButton(
                )
            },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.button)),
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
        ) {
            Text(text = "Start",
                fontSize = 20.sp)
        }
    }
}