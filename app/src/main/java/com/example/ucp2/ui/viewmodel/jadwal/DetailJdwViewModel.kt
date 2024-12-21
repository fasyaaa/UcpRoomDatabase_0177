package com.example.ucp2.ui.viewmodel.jadwal

import com.example.ucp2.data.entity.Jadwal

data class DetailUiState(
    val detailUiEvent: JadwalEvent = JadwalEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

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