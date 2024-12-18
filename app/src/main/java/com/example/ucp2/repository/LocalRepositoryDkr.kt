package com.example.ucp2.repository

import com.example.ucp2.data.dao.DokterDao
import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDkr(private val dokterDao: DokterDao): RepositoryDkr {
    override suspend fun insertDkr(dokter: Dokter) {
        dokterDao.InsertDokter(dokter)
    }

    override fun getAllDkr(): Flow<List<Dokter>> {
        return (dokterDao.getAllDokter())
    }
}