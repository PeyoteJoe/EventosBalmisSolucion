package com.pmdm.eventos.data.mocks.suscripcion

data class SuscripcionMock(
    val id: Int,
    val nick: String,
    val nombre: String,
    val correo: String,
    val metodoPago: String,
    val suscrito: Boolean
)
