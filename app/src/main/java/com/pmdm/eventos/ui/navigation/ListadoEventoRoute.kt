package com.pmdm.eventos.ui.navigation

import androidx.compose.runtime.toMutableStateList
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.eventos.ui.features.presentacion_eventos.eventos.ListadoEventoViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.eventos.ListadoEventosScreen

import kotlinx.serialization.Serializable

@Serializable
object ListadoEventoRoute

fun NavGraphBuilder.listadoEventosDestination(
    suscricion: Boolean,
    query: String,
    listadoEventoViewModel: ListadoEventoViewModel
) {
    composable<ListadoEventoRoute> {
        ListadoEventosScreen(
            suscricion = suscricion,
            listaEventos = listadoEventoViewModel.eventosUiState.filter {
                it.tipo.uppercase().contains(query.uppercase())
            }.toMutableStateList(),
            onClickFavoritos = listadoEventoViewModel::onClickFavoritos
        )
    }
}