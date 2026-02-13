package com.pmdm.eventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import com.pmdm.eventos.ui.features.presentacion_eventos.eventos.ListadoEventoViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion.SuscripcionViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.trending.TrendingViewModel

@Composable
fun EventosNavHost(
    query: String,
    navController: NavHostController,
    suscrito: Boolean,
    onSuscrito: (Boolean) -> Unit
) {
    val listadoEventoViewModel = hiltViewModel<ListadoEventoViewModel>()
    val suscripcionVewModel: SuscripcionViewModel = hiltViewModel()
    val trendingVewModel: TrendingViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ListadoEventoRoute) {

        listadoEventosDestination(
            suscricion = suscrito,
            query = query,
            listadoEventoViewModel = listadoEventoViewModel
        )
        suscriptionDestination(
            suscripcionViewModel = suscripcionVewModel,
            suscrito = onSuscrito
        )
        trendingDestination(trendingViewModel = trendingVewModel) {
            navController.navigate(route = InformacionTrendingRoute(id = it))
        }
        informacionTrendingDestination(eventoViewModel = listadoEventoViewModel)
    }
}