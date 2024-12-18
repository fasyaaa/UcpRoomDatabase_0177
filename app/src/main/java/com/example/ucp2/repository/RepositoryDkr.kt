package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDkr {
    suspend fun insertDkr(dokter: Dokter)

    fun getAllDkr(): Flow<List<Dokter>>
}