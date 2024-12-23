package com.example.ucp2.ui.view.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.ui.customwidget.DynamicSelectedField
import com.example.ucp2.ui.customwidget.TopAppBarCst
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.dokter.DkrUIState
import com.example.ucp2.ui.viewmodel.dokter.DokterEvent
import com.example.ucp2.ui.viewmodel.dokter.AddDokterViewModel
import com.example.ucp2.ui.viewmodel.dokter.FormErrorState
import kotlinx.coroutines.launch

@Composable
fun InsertDkrView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddDokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (modifier = modifier,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState) }
    ) {
            innerPadding -> //mengatur canvas yang di gambar
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
        ) {
            TopAppBarCst(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dokter",
                modifier = Modifier
            )
//                Isi Body
            InsertBodyDokter (
                uiState = uiState,
                onValueChange = { updateEvent -> viewModel.updateState(updateEvent) }, //Update state di view model
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData() //Simpan data
                    }
                    onNavigate()
                }
            )
        }
    }
}
@Composable
fun FormDokter(
    dokterEvent: DokterEvent = DokterEvent(),
    onValueChange: (DokterEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    val spesialisOptions = listOf("Dokter Bedah", "Dokter Jantung", "Dokter Mata", "Dokter Mulut", "Dokter Gigi", "Dokter Umum")
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.idDkr,
            onValueChange = {
                onValueChange(dokterEvent.copy(idDkr = it))
            },
            label = { Text("Nomer STR Dokter", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nomer STR ") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            shape = RoundedCornerShape((50.dp))
        )
        Text(
            text = errorState.idDkr ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.nama,
            onValueChange = {
                onValueChange(dokterEvent.copy(nama = it))
            },
            label = { Text("Nama Dokter", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            shape = RoundedCornerShape((50.dp))
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.klinik,
            onValueChange = {
                onValueChange(dokterEvent.copy(klinik = it))
            },
            label = { Text("Tempat Praktik", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.klinik != null,
            placeholder = { Text("Masukkan Lokasi Praktik") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            shape = RoundedCornerShape((50.dp))
        )
        Text(
            text = errorState.klinik ?: "",
            color = Color.Red
        )

        DynamicSelectedField(
            selectedValue = dokterEvent.spesialis,
            options = spesialisOptions,
            label = "Spesialis",
            placeholder = "Pilih Spesialis",
            onValueChangedEvent = {
                onValueChange(dokterEvent.copy(spesialis = it))
            },
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = errorState.spesialis ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.nohp,
            onValueChange = {
                onValueChange(dokterEvent.copy(nohp = it))
            },
            label = { Text("Nomer Telephone", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.nohp != null,
            placeholder = { Text("Masukkan Nomer Telephone") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            shape = RoundedCornerShape((50.dp))
        )
        Text(
            text = errorState.nohp ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.jamkerja,
            onValueChange = {
                onValueChange(dokterEvent.copy(jamkerja = it))
            },
            label = { Text("Jam Praktik", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.jamkerja != null,
            placeholder = { Text("Masukkan Ketersediaan Jam Praktik") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            shape = RoundedCornerShape((50.dp))
        )
        Text(
            text = errorState.jamkerja ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyDokter(
    modifier: Modifier = Modifier,
    onValueChange: (DokterEvent) -> Unit,
    uiState: DkrUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDokter(
            dokterEvent = uiState.dokterEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button)
            )
        ) {
            Text("Simpan")

        }
    }
}
