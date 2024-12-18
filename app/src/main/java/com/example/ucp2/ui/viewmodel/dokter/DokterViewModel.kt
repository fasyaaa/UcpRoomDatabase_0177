package com.example.ucp2.ui.viewmodel.dokter

import com.example.ucp2.data.entity.Dokter

data class DokterEvent(
    val idDkr: String = "",
    val nama: String = "",
    val spesialis : String = "",
    val klinik : String = "",
    val nohp : String = "",
    val jamkerja : String = ""
)

fun DokterEvent.toDokterEntity(): Dokter = Dokter(
    idDokter = idDkr,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    nohp = nohp,
    jamkerja = jamkerja
)