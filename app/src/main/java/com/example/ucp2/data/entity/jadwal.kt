package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal(
    @PrimaryKey
    val idJadwal : String,
    val namaDokter : String,
    val namaPasien : String,
    val nohp : String,
    val tglKon : String,
    val status : String
)