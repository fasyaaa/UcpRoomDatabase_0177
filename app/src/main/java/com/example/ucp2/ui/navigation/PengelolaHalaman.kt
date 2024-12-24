package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.Rs.RsSplashView
import com.example.ucp2.ui.view.dokter.HomeDkrView
import com.example.ucp2.ui.view.dokter.InsertDkrView
import com.example.ucp2.ui.view.jadwal.DetailJdwView
import com.example.ucp2.ui.view.jadwal.HomeJdwView
import com.example.ucp2.ui.view.jadwal.InsertJdwView

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiSplash.route
    )
    {
        composable(route = DestinasiSplash.route) {
            RsSplashView(onMulaiButton = {
                navController.navigate(
                    DestinasiHome.route
                )
            })
        }
        composable(route = DestinasiHome.route) {
            HomeDkrView(
                onClickDkr = {navController.navigate(DestinasiAddDkr.route)},
                onClickJdw = {navController.navigate(DestinasiHomeJdw.route)})
        }
        composable(route = DestinasiAddDkr.route){
            InsertDkrView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()},
                modifier = modifier
            )
        }
        composable(route = DestinasiHomeJdw.route){
            HomeJdwView(
                onBack = {navController.popBackStack()},
                onAddJdwl = {navController.navigate(DestinasiAddJdw.route)},
                onClickDtlJdw = { idJdw ->
                    navController.navigate("${DestinasiDetailJdw.route}/$idJdw")
                    println(
                        "PengelolaHalaman: idJdw =  $idJdw"
                    )},
                modifier = modifier
            )
        }
        composable(route = DestinasiAddJdw.route){
            InsertJdwView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()},
                modifier = modifier
            )
        }
        composable(
            DestinasiDetailJdw.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailJdw.idJdw) {
                    type = NavType.StringType
                }
            )
        ) {
            val idJdw = it.arguments?.getString(DestinasiDetailJdw.idJdw)
            idJdw?.let { id ->
                DetailJdwView(
                    onBack = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdateJdw.route}/$id") },
                    onDeleteClick = { navController.popBackStack() }
                )
            }
        }

    }
}
