package com.example.ucp2.ui.viewmodel.dokter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.repository.RepositoryDkr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeDkrViewModel (
    private val repositoryDkr: RepositoryDkr
): ViewModel(){
    val homeUiState: StateFlow<HomeDkrUiState> = repositoryDkr
        .getAllDkr()
        .filterNotNull()
        .map {
            HomeDkrUiState(listDkr = it.toList(),
                isLoading = false)
        }
        .onStart {
            emit(HomeDkrUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeDkrUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeDkrUiState(
                isLoading = true
            )
        )
}

data class HomeDkrUiState(
    val listDkr: List<Dokter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)