package com.pmdm.eventos.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pmdm.eventos.data.mocks.evento.EventoMock
import javax.inject.Inject
import javax.inject.Singleton

@Dao
interface EventoDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(evento: EventoEntity)

    @Query("SELECT * FROM eventos")
    suspend fun get(): List<EventoEntity>

    @Query("SELECT * FROM eventos WHERE id IN (:id)")
    suspend fun get(id:Int): EventoEntity

    @Update
    suspend fun update(evento: EventoEntity)

    @Query("SELECT COUNT(*) FROM eventos")
    suspend fun count(): Int
}