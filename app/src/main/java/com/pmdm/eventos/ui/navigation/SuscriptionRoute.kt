package com.pmdm.eventos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion.SuscripcionScreen
import com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion.SuscripcionViewModel
import kotlinx.serialization.Serializable

@Serializable
object SuscriptionRoute

fun NavGraphBuilder.suscriptionDestination(
    suscripcionViewModel: SuscripcionViewModel,
    suscrito : (Boolean) -> Unit
) {
    composable<SuscriptionRoute> {
        SuscripcionScreen (
            suscripcionUiState = suscripcionViewModel.suscripcionUiState,
            onSuscripcionEvent =  suscripcionViewModel::onSuscriptionEvent,
            onSuscrito = suscrito,
            validacionSuscripcionUiState = suscripcionViewModel.validacionSuscripcionUiState
        )
    }
}


