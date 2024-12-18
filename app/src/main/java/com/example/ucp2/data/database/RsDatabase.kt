package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DokterDao
import com.example.ucp2.data.dao.JadwalDao
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class RsDatabase: RoomDatabase(){

    abstract fun dokterDao(): DokterDao
    abstract fun jadwalDao(): JadwalDao

    companion object{
        @Volatile
        private var Instance: RsDatabase? = null

        fun getDatabase(context: Context): RsDatabase{
            return(Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    RsDatabase::class.java,
                    "RsDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}

