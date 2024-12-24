package com.example.ucp2.ui.viewmodel.jadwal

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryDkr
import com.example.ucp2.repository.RepositoryJdw
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddJadwalViewModel (private val repositoryJdw: RepositoryJdw, private val repositoryDkr: RepositoryDkr): ViewModel(){
    var uiStateJdwl by mutableStateOf(JdwUIState())

    val dokterListFlow = repositoryDkr.getAllDkr()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())


    fun updateState(jadwalEvent: JadwalEvent){
        uiStateJdwl = uiStateJdwl.copy(
            jadwalEvent = jadwalEvent,
        )
    }

    private fun validateField(): Boolean{
        val event = uiStateJdwl.jadwalEvent
        val isNoHpValid = event.noHp.matches(Regex("^[0-9]+$"))
        val errorState = FormErrorStateJdwl(
            namDkr = if(event.namDkr.isNotEmpty()) null else "Nama Dokter tidak boleh kosong",
            namPs = if(event.namPs.isNotEmpty()) null else "Nama Pasien tidak boleh kosong",
            noHp = if (event.noHp.isNotEmpty()) {
                if (isNoHpValid) null else "Nomor Handphone harus terdiri dari angka saja"
            } else {
                "Nomor Handphone tidak boleh kosong"
            },
            tglKon = if(event.tglKon.isNotEmpty()) null else "Tanggal Reservasi tidak boleh kosong",
            status = if(event.status.isNotEmpty()) null else "Status tidak boleh kosong"
        )
        uiStateJdwl = uiStateJdwl.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiStateJdwl.jadwalEvent
        if (validateField()) {
            viewModelScope.launch {
                try {
                    repositoryJdw.insertJdw(currentEvent.toJadwalEntity())
                    uiStateJdwl = uiStateJdwl.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorStateJdwl()
                    )
                } catch (e: Exception) {
                    uiStateJdwl = uiStateJdwl.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiStateJdwl = uiStateJdwl.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data anda"
            )
        }
    }
    fun resetSnackBarMessageJdwl() {
        uiStateJdwl = uiStateJdwl.copy(
            snackbarMessage = null
        )
    }
}


data class JdwUIState(
    val jadwalEvent: JadwalEvent = JadwalEvent(),
    val isEntryValid: FormErrorStateJdwl = FormErrorStateJdwl(),
    val snackbarMessage: String? = null,
)

data class FormErrorStateJdwl(
    val namDkr: String? = null,
    val namPs: String? = null,
    val noHp: String? = null,
    val tglKon: String? = null,
    val status: String? = null
){
    fun isValid(): Boolean {
        return namDkr == null &&
                namPs == null &&
                noHp == null &&
                tglKon == null &&
                status == null
    }
}

data class JadwalEvent(
    val idJdw: Int = 0,
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