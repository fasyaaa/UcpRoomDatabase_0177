package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal(
    @PrimaryKey(autoGenerate = true)
    val idJadwal : Int,
    val namaDokter : String,
    val namaPasien : String,
    val nohp : String,
    val tglKon : String,
    val status : String
)