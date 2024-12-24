package com.example.ucp2.ui.view.jadwal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.TopAppBarCst
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.jadwal.AddJadwalViewModel
import com.example.ucp2.ui.viewmodel.jadwal.UpdateJdwViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun UpdateJdwView(
    onNavigate: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModelList: AddJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModel: UpdateJdwViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.updateUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dokterList = viewModelList.dokterListFlow.collectAsState(initial = emptyList()).value

    LaunchedEffect(uiState.snackbarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackbarMessage?.let { message ->
            println("Snackbar message received: $message")
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold( topBar = {
        TopAppBarCst(
            onBack = onBack,
            judul = "Update Barang",
            showBackButton = true,
            modifier = modifier
        )
    },modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) })
    {
            padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyJadwal(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent) // Update state di ViewModel
                },
                dokList = dokterList.map {it},
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                })
        }
    }
}
