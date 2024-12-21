package com.example.ucp2.ui.viewmodel.jadwal

import com.example.ucp2.data.entity.Jadwal

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