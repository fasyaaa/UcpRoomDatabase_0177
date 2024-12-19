package com.example.ucp2.ui.viewmodel.jadwal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryJdw

class JadwalViewModel (private val repositoryJdw: RepositoryJdw): ViewModel(){
    var uiState by mutableStateOf(JdwUIState())

    fun updateState(jadwalEvent: JadwalEvent){
        uiState = uiState.copy(
            jadwalEvent = jadwalEvent,
        )
    }

    private fun validateField(): Boolean{
        val event = uiState.jadwalEvent
        val errorState = FormErrorState(

        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
}

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
){
    fun isValid(): Boolean {
        return idJdw == null &&
                namDkr == null &&
                namPs == null &&
                noHp == null &&
                tglKon == null &&
                status == null
    }
}

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