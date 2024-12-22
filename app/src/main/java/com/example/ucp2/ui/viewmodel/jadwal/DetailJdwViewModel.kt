package com.example.ucp2.ui.viewmodel.jadwal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryJdw
import com.example.ucp2.ui.navigation.DestinasiDetailJdw
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn



data class DetailUiState(
    val detailUiEvent: JadwalEvent = JadwalEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == JadwalEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != JadwalEvent()
}


fun Jadwal.toDetailUiEvent() : JadwalEvent{
    return  JadwalEvent(
        idJdw = idJadwal,
        namDkr = namaDokter,
        namPs = namaPasien,
        noHp = nohp,
        tglKon = tglKon,
        status = status
    )
}