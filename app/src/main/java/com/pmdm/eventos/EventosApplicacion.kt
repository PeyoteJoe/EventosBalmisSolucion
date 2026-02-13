package com.pmdm.eventos

import android.app.Application
import com.pmdm.eventos.data.EventoRepository
import com.pmdm.eventos.data.mocks.evento.EventoDaoMock
import com.pmdm.eventos.data.toEvento
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking

@HiltAndroidApp
class EventosApplicacion : Application() {
    @Inject
    lateinit var eventoRepository: EventoRepository
    @Inject
    lateinit var mockEventos: EventoDaoMock

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if (eventoRepository.count() == 0) {
                mockEventos.get().forEach { eventoMock -> eventoRepository.insert(evento = eventoMock.toEvento()) }
            }
        }
    }
}