package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.view.Rs.RsSplashView
import com.example.ucp2.ui.view.dokter.HomeDkrView
import com.example.ucp2.ui.view.dokter.InsertDkrView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
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
    }
}
