package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome: AlamatNavigasi{
    override val route = "home"
}

object DestinasiDetailJdw: AlamatNavigasi{
    override val route = "detail"
    const val idJdw = "idJadwal"
    val routesWithArg = "$route/{$idJdw}"
}

object DestinasiUpdateJdw: AlamatNavigasi{
    override val route = "update"
    const val idJdw = "idJadwal"
    val routesWithArg = "$route/{$idJdw}"
}

object DestinasiSplash: AlamatNavigasi{
    override val route = "splash"
}