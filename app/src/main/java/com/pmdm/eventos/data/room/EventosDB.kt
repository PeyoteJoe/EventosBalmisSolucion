package com.pmdm.eventos.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [EventoEntity::class],
    version = 1
)
abstract class EventoDB : RoomDatabase() {
    abstract fun eventoDao(): EventoDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            EventoDB::class.java, "eventos.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}