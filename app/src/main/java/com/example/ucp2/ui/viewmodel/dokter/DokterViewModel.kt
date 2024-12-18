package com.example.ucp2.ui.viewmodel.dokter

import com.example.ucp2.data.entity.Dokter

data class FormErrorState(
    val idDkr: String? = null,
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val nohp: String? = null,
    val jamkerja: String? = null
){
    fun isValid(): Boolean{
        return idDkr == null &&
                nama == null &&
                spesialis == null &&
                klinik == null &&
                nohp == null &&
                jamkerja == null
    }
}

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