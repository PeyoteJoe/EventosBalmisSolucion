package com.pmdm.eventos.ui.features.presentacion_eventos.eventos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.eventos.data.EventoRepository
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoUiState
import com.pmdm.eventos.ui.features.presentacion_eventos.toEvento
import com.pmdm.eventos.ui.features.presentacion_eventos.toEventoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ListadoEventoViewModel @Inject constructor(
    private val eventoRepository: EventoRepository
) : ViewModel() {

    var eventosUiState : SnapshotStateList<EventoUiState> = runBlocking {
                                                                eventoRepository.get()
                                                                    .map { it.toEventoUiState() }
                                                                    .toMutableStateList()
                                                            }
    fun onClickFavoritos(id: Int) {
        viewModelScope.launch {
            val eventoPulsado = eventosUiState.find { it.id == id }
            val indice = eventosUiState.indexOf(eventoPulsado)

            eventosUiState[indice] = eventosUiState[indice].copy(siguiendo = !eventosUiState[indice].siguiendo)

            if (eventosUiState[indice].siguiendo)
                eventoRepository.incrementaSeguidor(eventosUiState[indice].toEvento())
            else
                eventoRepository.decrementaSeguidor(eventosUiState[indice].toEvento())

            eventosUiState[indice] = eventosUiState[indice].copy(seguidores = eventoRepository.get()[indice].seguidores)
        }
    }
}