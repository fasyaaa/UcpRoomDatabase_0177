package com.example.ucp2.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun TopAppBarCst(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center // Pastikan konten di tengah
    ) {
        if (showBackButton) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Kembali")
                }
                Spacer(modifier = Modifier.weight(2f))
            }
        }

        // Teks judul
        Text(
            text = judul,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onKembali: () -> Unit,
    showProfile: Boolean = true,
    showBackButton: Boolean = true,
    title: String,
    onAddDtr: () -> Unit,
    onAddJdw: () -> Unit,
    add1: String,
    add2: String,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(LocalConfiguration.current.screenHeightDp.dp / 2)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.background),
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
        ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (showBackButton) {
                        IconButton(onClick = onKembali) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = colorResource(id = R.color.white)
                            )
                        }
                    }

                    if (showProfile) {
                        Image(
                            painter = painterResource(id = R.drawable.dokter),
                            contentDescription = null,
                            modifier = Modifier
                                .size(250.dp)
                                .padding(top = 30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(2.dp))

                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 30.dp)
                .padding(horizontal = 20.dp)
                .width(320.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    onClick = onAddDtr,
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white))
                ) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "",
                            modifier = Modifier,
                            colorResource(id = R.color.black)
                        )
                        Text(
                            text = add1,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(80.dp)
                        .background(Color.Gray)
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    onClick = onAddJdw,
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white))
                ) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier,
                            colorResource(id = R.color.black)
                        )
                        Text(
                            text = add2,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar2(
    modifier: Modifier = Modifier,
    onKembali: () -> Unit,
    showProfile: Boolean = true,
    showBackButton: Boolean = true,
    onQuit: () -> Unit,
    onInsJdw: () -> Unit,
    add1: String,
    add2: String
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(LocalConfiguration.current.screenHeightDp.dp / 2)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.background),
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Jadwal",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 60.sp,
                    modifier = Modifier
                        .padding(top = 60.dp)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(350.dp).padding(bottom = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calender),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(300.dp)
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))

            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 30.dp)
                .padding(horizontal = 20.dp)
                .width(320.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    onClick = onQuit,// tanda 1
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "",
                            modifier = Modifier,
                            colorResource(id = R.color.black)
                        )
                        Text(
                            text = add1,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(80.dp)
                        .background(Color.Gray)
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    onClick = onInsJdw,
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier,
                            colorResource(id = R.color.black)
                        )
                        Text(
                            text = add2,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}