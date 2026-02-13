package com.pmdm.eventos.ui.features.presentacion_eventos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventoViewModel @Inject constructor() : ViewModel() {

    var suscrito by mutableStateOf(false)
        private set

    var query by mutableStateOf("")
        private set

    val suggestion: List<String> = listOf("Musica", "Humor", "Teatro", "Empresa", "Gastronomia")

    fun onSuscrito(suscrito: Boolean) {
        this.suscrito = suscrito
    }
    fun onQueryChange(filtro: String) {
        this.query = filtro
    }
}