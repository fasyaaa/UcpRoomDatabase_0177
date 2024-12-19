package com.example.ucp2.ui.viewmodel.jadwal

import com.example.ucp2.data.entity.Jadwal

data class JdwUIState(
    val jadwalEvent: JadwalEvent = JadwalEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snacbarMessage: String? = null
)

data class FormErrorState(
    val idJdw: String? = null,
    val namDkr: String? = null,
    val namPs: String? = null,
    val noHp: String? = null,
    val tglKon: String? = null,
    val status: String? = null
)

data class JadwalEvent(
    val idJdw: String = "",
    val namDkr: String = "",
    val namPs: String = "",
    val noHp: String = "",
    val tglKon: String = "",
    val status: String = ""
)

fun JadwalEvent.toJadwalEntity(): Jadwal = Jadwal(
    idJadwal = idJdw,
    namaDokter = namDkr,
    namaPasien = namPs,
    nohp = noHp,
    tglKon = tglKon,
    status = status
)