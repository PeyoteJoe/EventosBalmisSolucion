package com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion

import com.pmdm.eventos.models.Suscripcion

data class SuscripcionUiState(
    val id: Int = 0,
    val nick: String = "",
    val nombre: String = "",
    val correo: String = "",
    val metodoPago: String = "",
    val suscrito: Boolean = false
)

fun Suscripcion.toSuscripcionUiState() = SuscripcionUiState(
    id = id,
    nick = nick,
    nombre = nombre,
    correo = correo,
    metodoPago = metodoPago,
    suscrito = suscrito
)
