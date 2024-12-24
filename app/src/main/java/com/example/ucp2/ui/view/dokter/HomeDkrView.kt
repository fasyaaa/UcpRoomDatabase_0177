package com.example.ucp2.ui.view.dokter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.dokter.HomeDkrUiState
import com.example.ucp2.ui.viewmodel.dokter.HomeDkrViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeDkrView(
    viewModel: HomeDkrViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onClickDkr: () -> Unit = {},
    onClickJdw: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Sejahtera\nHospital",
                onAddDtr = onClickDkr,
                onAddJdw = onClickJdw,
                add1 = "Tambah Dokter",
                add2 = "Tambah Jadwal",
                showBackButton = false,
                showProfile = true,
                onKembali = {},
                modifier = modifier
            )
        },
    ) { innerPadding ->

        val homeUiState by viewModel.homeUiState.collectAsState()
        Spacer(modifier = Modifier.padding(8.dp))
        BodyHomeMhsView(
            homeUiState = homeUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun BodyHomeMhsView(
    homeUiState: HomeDkrUiState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when{
        homeUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeUiState.listDkr.isEmpty() ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data Dokter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListDokter(
                lisDokter = homeUiState.listDkr,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListDokter(
    lisDokter: List<Dokter>,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = lisDokter,
            itemContent = {dktr ->
                ExpCardDktr(dktr = dktr)
            }
        )
    }
}



@Composable
fun ExpCardDktr(
    dktr: Dokter,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 30.dp),
        onClick = { isExpanded = !isExpanded },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dktr.nama,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Data Dokter" else "Dokter",
                    tint = Color.Black
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = colorResource(id = R.color.black), thickness = 1.dp)

                    InfoRow(
                        icon = Icons.Filled.AccountCircle,
                        judul = "No STR",
                        isinya = dktr.idDokter,
                    )

                    InfoRow(
                        icon = Icons.Filled.Info,
                        judul = "Spesialis",
                        isinya = dktr.spesialis,
                    )

                    InfoRow(
                        icon = Icons.Filled.LocationOn,
                        judul = "Klinik",
                        isinya = dktr.klinik

                    )

                    InfoRow(
                        icon = Icons.Filled.Call,
                        judul = "Kontak",
                        isinya = dktr.nohp
                    )

                    InfoRow(
                        icon = Icons.Filled.DateRange,
                        judul = "Jam Kerja",
                        isinya = dktr.jamkerja
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector,
    judul: String,
    isinya: String,
    modifier: Modifier = Modifier
) {
    val specialisClr = mapOf(
        "Dokter Bedah" to colorResource(id = R.color.specialis1),
        "Dokter Jantung" to colorResource(id = R.color.specialis2),
        "Dokter Mata" to colorResource(id = R.color.specialis3),
        "Dokter Mulut" to colorResource(id = R.color.specialis4),
        "Dokter Gigi" to colorResource(id = R.color.specialis5),
        "Dokter Umum" to colorResource(id = R.color.specialis6)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorResource(id = R.color.specialis2),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = judul,
                fontSize = 12.sp,
                color = colorResource(id = R.color.black)
            )
            Text(
                text = isinya,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = specialisClr.getOrDefault(isinya, Color.Black)
            )
        }
    }
}

