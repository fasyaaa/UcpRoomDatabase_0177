package com.example.ucp2.ui.viewmodel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.repository.RepositoryDkr
import kotlinx.coroutines.launch

class AddDokterViewModel (private val repositoryDkr: RepositoryDkr): ViewModel(){
    var uiState by mutableStateOf(DkrUIState())

    fun updateState(dokterEvent: DokterEvent){
        uiState = uiState.copy(
            dokterEvent = dokterEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.dokterEvent
        val isNohpValid = event.nohp.matches(Regex("^[0-9]+$"))
        val errorState = FormErrorState(
            idDkr = if(event.idDkr.isNotEmpty()) null else "Nomer Surat Tanda Registrasi tidak boleh kosong",
            nama = if(event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            spesialis = if(event.spesialis.isNotEmpty()) null else "Spesialis tidak boleh kosong",
            klinik = if(event.klinik.isNotEmpty()) null else "Klinik tidak boleh kosong",
            nohp = if (event.nohp.isNotEmpty()) {
                if (isNohpValid) null else "Nomor Handphone harus terdiri dari angka saja"
            } else {
                "Nomor Handphone tidak boleh kosong"
            },
            jamkerja = if(event.jamkerja.isNotEmpty()) null else "Jam praktik tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.dokterEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDkr.insertDkr(currentEvent.toDokterEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        dokterEvent = DokterEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)

    }
}


data class DkrUIState(
    val dokterEvent: DokterEvent = DokterEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

data class FormErrorState(
    val idDkr: String? = null,
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val nohp: String? = null,
    val jamkerja: String? = null
){
    fun isValid(): Boolean{
        return idDkr == null &&
                nama == null &&
                spesialis == null &&
                klinik == null &&
                nohp == null &&
                jamkerja == null
    }
}

data class DokterEvent(
    val idDkr: String = "",
    val nama: String = "",
    val spesialis : String = "",
    val klinik : String = "",
    val nohp : String = "",
    val jamkerja : String = ""
)

fun DokterEvent.toDokterEntity(): Dokter = Dokter(
    idDokter = idDkr,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    nohp = nohp,
    jamkerja = jamkerja
)