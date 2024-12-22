package com.example.ucp2.ui.viewmodel.jadwal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryJdw
import com.example.ucp2.ui.navigation.DestinasiDetailJdw
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailJdwViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryJdw: RepositoryJdw
): ViewModel(){
    private val _idJdw: String = checkNotNull(savedStateHandle[DestinasiDetailJdw.idJdw])

    val detailUiState: StateFlow<DetailUiState> = repositoryJdw.getJdw(_idJdw)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true
            )
        )

    fun deleteJdw(){
        detailUiState.value.detailUiEvent.toJadwalEntity().let {
            viewModelScope.launch {
                repositoryJdw.deleteJdw(it)
            }
        }
    }
}

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