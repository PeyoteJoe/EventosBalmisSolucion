package com.pmdm.eventos.data

import com.pmdm.eventos.data.mocks.evento.EventoMock
import com.pmdm.eventos.data.mocks.suscripcion.SuscripcionMock
import com.pmdm.eventos.data.room.EventoEntity
import com.pmdm.eventos.models.Evento
import com.pmdm.eventos.models.Suscripcion

fun EventoEntity.toEvento() = Evento(
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

fun EventoMock.toEvento() = Evento(
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

fun Evento.toEventoEntity() = EventoEntity(
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

fun SuscripcionMock.toSuscripcion() = Suscripcion(
    id = id,
    nick = nick,
    nombre = nombre,
    correo = correo,
    metodoPago = metodoPago,
    suscrito = suscrito
)
