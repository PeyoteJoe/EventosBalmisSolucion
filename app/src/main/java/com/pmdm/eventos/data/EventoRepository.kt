package com.pmdm.eventos.data

import com.pmdm.eventos.data.mocks.suscripcion.SuscripcionDaoMock
import com.pmdm.eventos.data.room.EventoDao
import com.pmdm.eventos.models.Evento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class EventoRepository @Inject constructor(
    private val eventosDao: EventoDao,
    private val suscripcionDaoMock: SuscripcionDaoMock
) {
    suspend fun count() = withContext(Dispatchers.IO) {
        eventosDao.count()
    }

    suspend fun get(): List<Evento> = withContext(Dispatchers.IO) {
        eventosDao.get().map { it.toEvento() }
    }

    suspend fun insert(evento: Evento) = withContext(Dispatchers.IO) {
        eventosDao.insert(evento.toEventoEntity())
    }

    suspend fun incrementaSeguidor(evento: Evento) = withContext(Dispatchers.IO) {
        val aux = evento.copy(seguidores = evento.seguidores + 1)
        eventosDao.update(aux.toEventoEntity())
    }

    suspend fun decrementaSeguidor(evento: Evento) = withContext(Dispatchers.IO) {
        val aux = evento.copy(seguidores = evento.seguidores - 1)
        eventosDao.update(aux.toEventoEntity())
    }

    suspend fun getSuscriptores() = withContext(Dispatchers.IO) {
        suscripcionDaoMock.get().map { it.toSuscripcion() }
    }
}