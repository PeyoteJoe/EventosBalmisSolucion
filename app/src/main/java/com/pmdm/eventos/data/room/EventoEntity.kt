package com.pmdm.eventos.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos")
data class EventoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imagen: String?,
    val titulo: String,
    val fecha: String,
    val lugar: String,
    val realizado: String,
    val precio: Float,
    val seguidores: Int,
    val tipo: String
)

