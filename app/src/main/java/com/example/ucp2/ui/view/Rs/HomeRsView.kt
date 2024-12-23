package com.example.ucp2.ui.view.Rs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ucp2.R
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.dokter.HomeDkrViewModel
import com.example.ucp2.ui.viewmodel.jadwal.JadwalViewModel

@Composable
fun HomeRsView(
    modifier: Modifier = Modifier,
    viewModel: HomeDkrViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onClickDkr: (String) -> Unit,
    onClickJdw: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bgDsp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 48.dp,bottomEnd = 48.dp))
                .background(color = colorResource(R.color.background))
                .padding(bottom = 32.dp)
                .padding(top = 20.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 24.dp)
            ) {
                Column {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home Icon",
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(Modifier.padding(3.dp))
                    Text(
                        text = "Welcome",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "to Our Warehouse",
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Box(
                Modifier.align(Alignment.CenterEnd)
                    .padding(24.dp)
                    .padding(top = 12.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(40.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ManageBox(
            title = "Manage Supplier",
            description = "Kelola data supplier di sini. Pantau dan atur informasi supplier dengan cepat.",
            backgroundColor = Color(0xFF00C853),
            iconResource = R.drawable.logo,
            onClick = {onClickDkr}
        )

        ManageBox(
            title = "Manage Barang",
            description = "Kelola data barang di sini. Tambahkan, ubah, atau hapus data barang dengan mudah.",
            backgroundColor = Color(0xFF007BFF),
            iconResource = R.drawable.logo,
            onClick = { onClickJdw }
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "Jaga performa gudang Anda dengan terus memperbarui data barang dan supplier. Aplikasi ini dirancang untuk mendukung kebutuhan Anda!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ManageBox(
    title: String,
    description: String,
    backgroundColor: Color,
    iconResource: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}