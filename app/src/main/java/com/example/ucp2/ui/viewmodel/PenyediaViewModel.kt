package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.RsSejahteraApp
import com.example.ucp2.ui.viewmodel.dokter.DokterViewModel
import com.example.ucp2.ui.viewmodel.jadwal.DetailJdwViewModel
import com.example.ucp2.ui.viewmodel.jadwal.JadwalViewModel
import com.example.ucp2.ui.viewmodel.jadwal.UpdateJdwViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                rsSejahteraApp().containerApp.repositoryDkr
            )
        }

        initializer {
            JadwalViewModel(
                rsSejahteraApp().containerApp.repositoryJdw
            )
        }

        initializer {
            HomeRsViewModel(
                rsSejahteraApp().containerApp.repositoryDkr
            )
        }

        initializer {
            DetailJdwViewModel(
                createSavedStateHandle(),
                rsSejahteraApp().containerApp.repositoryJdw
            )
        }

        initializer {
            UpdateJdwViewModel(
                createSavedStateHandle(),
                rsSejahteraApp().containerApp.repositoryJdw
            )
        }
    }
}

fun CreationExtras.rsSejahteraApp(): RsSejahteraApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsSejahteraApp)