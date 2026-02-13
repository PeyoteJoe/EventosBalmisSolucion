package com.pmdm.eventos.models

data class Suscripcion(
    val id: Int,
    val nick: String,
    val nombre: String,
    val correo: String,
    val metodoPago: String,
    val suscrito: Boolean
)
