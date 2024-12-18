package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.RsSejahteraApp
import com.example.ucp2.ui.viewmodel.dokter.HomeDkrViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            HomeDkrViewModel(
                rsSejahtera().containerApp.repositoryDkr
            )
        }
    }
}

fun CreationExtras.rsSejahtera(): RsSejahteraApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsSejahteraApp)