package com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion

sealed interface SuscripcionEvent {
    object OnAceptaSuscripcion: SuscripcionEvent
    object OnCancelaSuscripcion: SuscripcionEvent
    data class OnCambiaNombre(val nombre: String) : SuscripcionEvent
    data class OnCambiaCorreo(val correo: String) : SuscripcionEvent
    data class OnCambiaNick(val nick: String) : SuscripcionEvent
    data class OnCambiaMetodoPago(val metodoPago: String) : SuscripcionEvent
}