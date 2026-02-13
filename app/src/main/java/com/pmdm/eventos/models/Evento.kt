package com.pmdm.eventos.models

data class Evento(
    val id: Int,
    val imagen: String,
    val titulo: String,
    val fecha: String,
    val lugar: String,
    val realizado: String,
    val precio: Float,
    val seguidores: Int,
    val tipo: String
)

