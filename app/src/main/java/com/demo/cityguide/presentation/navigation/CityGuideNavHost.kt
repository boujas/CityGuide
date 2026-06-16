package com.demo.cityguide.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.cityguide.presentation.details.PlaceDetailsRoute
import com.demo.cityguide.presentation.home.HomeRoute

@Composable
fun CityGuideNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination
    ) {
        composable<HomeDestination> {
            HomeRoute(
                onPlaceClick = { placeId ->
                    navController.navigate(
                        PlaceDetailsDestination(placeId = placeId)
                    )
                }
            )
        }

        composable<PlaceDetailsDestination> {
            PlaceDetailsRoute(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}