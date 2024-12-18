package com.example.ucp2.repository

import com.example.ucp2.data.dao.JadwalDao
import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJdw(private val jadwalDao: JadwalDao): RepositoryJdw {
    override suspend fun insertJdw(jadwal: Jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override suspend fun updateJdw(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }

    override suspend fun deleteJdw(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    override fun getAllDkr(): Flow<List<Jadwal>> {
        return (jadwalDao.getAllJadwal())
    }

    override fun getMhs(idJdw: String): Flow<Jadwal> {
        return (jadwalDao.getJadwal(idJdw))
    }
}