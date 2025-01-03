package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {
    @Insert
    suspend fun insertJadwal(
        jadwal: Jadwal
    ):Long
    @Update
    suspend fun updateJadwal(
        jadwal: Jadwal
    ):Int
    @Delete
    suspend fun deleteJadwal(
        jadwal: Jadwal
    ):Int

    @Query("SELECT * FROM jadwal ORDER BY tglKon ASC")
    fun getAllJadwal(): Flow<List<Jadwal>>

    @Query("SELECT * FROM jadwal WHERE idJadwal = :idJadwal")
    fun getJadwal(idJadwal: String): Flow<Jadwal>
}