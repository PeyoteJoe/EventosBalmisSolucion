package com.pmdm.eventos.ui.features.presentacion_eventos.trending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.eventos.data.EventoRepository
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoUiState
import com.pmdm.eventos.ui.features.presentacion_eventos.toEventoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val eventoRepository: EventoRepository
) : ViewModel() {

    var eventosUiState by mutableStateOf(listOf<EventoUiState>())
        private set

    init {
        viewModelScope.launch {
            eventosUiState =
                eventoRepository.get().map { e -> e.toEventoUiState() }.toMutableStateList()
                    .sortedBy { it.seguidores }
                    .reversed().take(3)
        }
    }
}