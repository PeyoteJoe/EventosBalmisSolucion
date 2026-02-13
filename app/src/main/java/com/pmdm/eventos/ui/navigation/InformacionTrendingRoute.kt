package com.pmdm.eventos.ui.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoUiState
import com.pmdm.eventos.ui.features.presentacion_eventos.eventos.ListadoEventoViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.trending.InformacionTrendingScreen
import kotlinx.serialization.Serializable

@Serializable
data class InformacionTrendingRoute(val id: Int)

fun NavGraphBuilder.informacionTrendingDestination(
    eventoViewModel: ListadoEventoViewModel,
) {
    composable<InformacionTrendingRoute> { backStackEntry ->
        val datoId = remember { backStackEntry.toRoute<InformacionTrendingRoute>().id }
        val eventoUiState = eventoViewModel.eventosUiState.find { it.id == datoId }
        InformacionTrendingScreen(
            eventosUiState = eventoUiState ?: EventoUiState()
        )
    }
}
