package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dokter")
data class Dokter(
    @PrimaryKey
    val idDokter : String,
    val nama : String,
    val spesialis : String,
    val klinik : String,
    val nohp : String,
    val jamkerja : String
)