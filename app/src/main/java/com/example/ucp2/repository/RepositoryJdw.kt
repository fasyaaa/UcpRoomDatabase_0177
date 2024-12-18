package com.example.ucp2.repository

import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJdw {
    suspend fun insertJdw(jadwal: Jadwal)

    suspend fun updateJdw(jadwal: Jadwal)

    suspend fun deleteJdw(jadwal: Jadwal)

    fun getAllDkr(): Flow<List<Jadwal>>

    fun getMhs(idJdw: String): Flow<Jadwal>
}