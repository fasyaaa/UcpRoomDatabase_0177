package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.RsSejahteraApp
import com.example.ucp2.ui.viewmodel.dokter.AddDokterViewModel
import com.example.ucp2.ui.viewmodel.dokter.HomeDkrViewModel
import com.example.ucp2.ui.viewmodel.jadwal.AddJadwalViewModel
import com.example.ucp2.ui.viewmodel.jadwal.DetailJdwViewModel
import com.example.ucp2.ui.viewmodel.jadwal.HomeJdwViewModel
import com.example.ucp2.ui.viewmodel.jadwal.UpdateJdwViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            AddDokterViewModel(
                rsSejahteraApp().containerApp.repositoryDkr
            )
        }

        initializer {
            AddJadwalViewModel(
                rsSejahteraApp().containerApp.repositoryJdw,
                rsSejahteraApp().containerApp.repositoryDkr
            )
        }

        initializer {
            HomeDkrViewModel(
                rsSejahteraApp().containerApp.repositoryDkr
            )
        }

        initializer {
            HomeJdwViewModel(
                rsSejahteraApp().containerApp.repositoryJdw
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
                rsSejahteraApp().containerApp.repositoryJdw,
                rsSejahteraApp().containerApp.repositoryDkr

            )
        }
    }
}

fun CreationExtras.rsSejahteraApp(): RsSejahteraApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsSejahteraApp)