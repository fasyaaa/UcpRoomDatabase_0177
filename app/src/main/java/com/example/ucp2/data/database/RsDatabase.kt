package com.example.ucp2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DokterDao
import com.example.ucp2.data.dao.JadwalDao
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class RsDatabase: RoomDatabase(){

    abstract fun dokterDao(): DokterDao
    abstract fun jadwalDao(): JadwalDao
}

