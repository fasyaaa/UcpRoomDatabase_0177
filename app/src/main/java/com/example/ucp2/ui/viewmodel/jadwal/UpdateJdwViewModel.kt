package com.example.ucp2.ui.viewmodel.jadwal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryJdw
import com.example.ucp2.ui.navigation.DestinasiUpdateJdw
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateJdwViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryJdw: RepositoryJdw
): ViewModel(){
    var updateUIState by mutableStateOf(JdwUIState())
        private set

    private val _idJdw: String = checkNotNull(savedStateHandle[DestinasiUpdateJdw.idJdw])

    init {
        viewModelScope.launch {
            updateUIState = repositoryJdw.getJdw(_idJdw)
                .filterNotNull()
                .first()
                .toUIStateJdw()
        }
    }
    fun updateState(jadwalEvent: JadwalEvent){
        updateUIState = updateUIState.copy(
            jadwalEvent = jadwalEvent
        )
    }
    fun validateFields(): Boolean{
        val event = updateUIState.jadwalEvent
        val isNoHpValid = event.noHp.matches(Regex("^[0-9]+$"))
        val errorState = FormErrorState(
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
        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun updateData(){
        val currentEvent = updateUIState.jadwalEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryJdw.updateJdw(currentEvent.toJadwalEntity())
                    updateUIState = updateUIState.copy(
                        snackbarMessage = "Data berhasil diupdate",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackbarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackbarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackbarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetSnackBarMessage(){
        updateUIState = updateUIState.copy(snackbarMessage = null)
    }
}

fun Jadwal.toUIStateJdw() : JdwUIState = JdwUIState(
    jadwalEvent = this.toDetailUiEvent()
)