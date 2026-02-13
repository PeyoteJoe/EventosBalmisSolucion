package com.pmdm.eventos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.eventos.ui.features.presentacion_eventos.trending.TrendingScreen
import com.pmdm.eventos.ui.features.presentacion_eventos.trending.TrendingViewModel
import kotlinx.serialization.Serializable

@Serializable
object TrendingRoute
fun NavGraphBuilder.trendingDestination(
    trendingViewModel: TrendingViewModel,
    onNavigateToInformacionTrending: (Int) -> Unit
) {
    composable<TrendingRoute> {
        TrendingScreen(
            listaEventos = trendingViewModel.eventosUiState,
            onNavigateToInformacionTrending = onNavigateToInformacionTrending
        )
    }
}
