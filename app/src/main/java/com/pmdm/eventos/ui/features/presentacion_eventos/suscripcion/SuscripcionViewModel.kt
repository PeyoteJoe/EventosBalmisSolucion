package com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.eventos.data.EventoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuscripcionViewModel @Inject constructor(
    private val eventoRepository: EventoRepository,
    private val validadorSuscripcion: ValidadorSuscripcion
) : ViewModel() {

    var suscripcionUiState: SuscripcionUiState by mutableStateOf(value = SuscripcionUiState())
        private set

    var validacionSuscripcionUiState by mutableStateOf(value = ValidacionSuscripcionUiState())
        private set

    init {
        viewModelScope.launch {
            suscripcionUiState = eventoRepository.getSuscriptores()[0].toSuscripcionUiState()
        }
    }

    fun onSuscriptionEvent(suscripcionEvent: SuscripcionEvent) {
        when (suscripcionEvent) {
            is SuscripcionEvent.OnCancelaSuscripcion -> {
                suscripcionUiState = suscripcionUiState.copy(suscrito = false)
            }

            is SuscripcionEvent.OnAceptaSuscripcion -> {
                suscripcionUiState = suscripcionUiState.copy(suscrito = true)
            }

            is SuscripcionEvent.OnCambiaNick -> {
                suscripcionUiState = suscripcionUiState.copy(nick = suscripcionEvent.nick)
            }

            is SuscripcionEvent.OnCambiaNombre -> {
                suscripcionUiState = suscripcionUiState.copy(nombre = suscripcionEvent.nombre)
            }

            is SuscripcionEvent.OnCambiaCorreo -> {
                suscripcionUiState = suscripcionUiState.copy(
                    correo = suscripcionEvent.correo
                )
                validacionSuscripcionUiState = validacionSuscripcionUiState.copy(
                    validacionCorreo = validadorSuscripcion.validadorCorreo.valida(suscripcionEvent.correo)
                )
            }

            is SuscripcionEvent.OnCambiaMetodoPago -> {
                suscripcionUiState =
                    suscripcionUiState.copy(metodoPago = suscripcionEvent.metodoPago)
            }
        }
    }
}