package com.example.ucp2.ui.view.jadwal

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.ui.customwidget.DynamicSelectedField
import com.example.ucp2.ui.customwidget.TopAppBarCst
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.jadwal.AddJadwalViewModel
import com.example.ucp2.ui.viewmodel.jadwal.FormErrorStateJdwl
import com.example.ucp2.ui.viewmodel.jadwal.JadwalEvent
import com.example.ucp2.ui.viewmodel.jadwal.JdwUIState
import kotlinx.coroutines.launch

@Composable
fun InsertJdwView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiStateJdwl
    val dokterList by viewModel.dokterListFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessageJdwl()
            }
        }
    }

    Scaffold (modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
                judul = "Tambah Jadwal",
                modifier = Modifier
            )
//                Isi Body
            InsertBodyJadwal(
                uiState = uiState,
                onValueChange = { updateEvent -> viewModel.updateState(updateEvent) }, //Update state di view model
                dokList = dokterList,
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
fun FormJadwal(
    jadwalEvent: JadwalEvent = JadwalEvent(),
    onValueChange: (JadwalEvent) -> Unit = {},
    errorState: FormErrorStateJdwl = FormErrorStateJdwl(),
    dokList: List<Dokter>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        DynamicSelectedField(
            selectedValue = jadwalEvent.namDkr,
            options = dokList.map { it.nama }.also {
                println("Dropdown options: $it")
            },
            label = "Nama Dokter",
            placeholder = "Pilih Dokter Spesialis",
            onValueChangedEvent = {
                onValueChange(jadwalEvent.copy(namDkr = it))
            },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.namPs,
            onValueChange = {
                onValueChange(jadwalEvent.copy(namPs = it))
            },
            label = { Text("Nama Pasien", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.namPs != null,
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
            text = errorState.namPs ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.noHp,
            onValueChange = {
                onValueChange(jadwalEvent.copy(noHp = it))
            },
            label = { Text("Nomer Telephone", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.noHp != null,
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
            text = errorState.noHp ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.tglKon,
            onValueChange = {
                onValueChange(jadwalEvent.copy(tglKon = it))
            },
            label = { Text("Tanggal Konsultasi", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.tglKon != null,
            placeholder = { Text("Masukkan Tanggal Konsultasi") },
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
            text = errorState.tglKon ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.status,
            onValueChange = {
                onValueChange(jadwalEvent.copy(status = it))
            },
            label = { Text("Status", modifier = Modifier, color = colorResource(id = R.color.black)) },
            isError = errorState.status != null,
            placeholder = { Text("Masukkan Status") },
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
            text = errorState.status ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyJadwal(
    modifier: Modifier = Modifier,
    onValueChange: (JadwalEvent) -> Unit,
    uiState: JdwUIState,
    dokList: List<Dokter>,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormJadwal(
            jadwalEvent = uiState.jadwalEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            dokList = dokList,
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
