package com.pmdm.eventos.ui.features.presentacion_eventos

import com.pmdm.eventos.models.Evento

data class EventoUiState(
    val id: Int = 0,
    val imagen: String? = null,
    val titulo: String = "",
    val fecha: String = "",
    val lugar: String = "",
    val realizado: String = "",
    val precio: Float = 0.0f,
    val seguidores: Int = 0,
    val siguiendo: Boolean = false,
    val tipo: String = ""
)

fun EventoUiState.toEvento() = Evento(
    id = this.id,
    imagen = this.imagen!!,
    titulo = this.titulo,
    fecha = this.fecha,
    lugar = this.lugar,
    realizado = this.realizado,
    precio = this.precio,
    seguidores = this.seguidores,
    tipo = this.tipo
)

fun Evento.toEventoUiState() = EventoUiState(
    id = this.id,
    imagen = this.imagen,
    titulo = this.titulo,
    fecha = this.fecha,
    lugar = this.lugar,
    realizado = this.realizado,
    precio = this.precio,
    seguidores = this.seguidores,
    tipo = this.tipo
)