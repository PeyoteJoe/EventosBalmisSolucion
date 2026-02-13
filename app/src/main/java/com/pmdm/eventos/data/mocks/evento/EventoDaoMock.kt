package com.pmdm.eventos.data.mocks.evento

import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventoDaoMock @Inject constructor() {
    private val listadoEventosMock = mutableListOf(
        EventoMock(
            id = 1,
            imagen = "imagen1",
            titulo = "VERMOOD OPEN BAR SAUSALITO",
            fecha = "Saturday, November 16 · 2 - 4pm CET",
            lugar = "Sausalito Premium Club by Puerto",
            realizado = "By SAUSALITO PREMUIM CLUB by Puerto",
            precio = 10f,
            seguidores = 23,
            tipo = "gastronomia copas"
        ),
        EventoMock(
            id = 2,
            imagen = "imagen2",
            titulo = "English Stand-up Comedy in Alicante: We are NOT From Here!",
            fecha = "Sunday, November 10 · 6 - 7:30pm CET",
            lugar = "El Refugio café art nature - arte - bar",
            realizado = "By Madrid Comedy Lab",
            precio = 5.77f,
            seguidores = 57,
            tipo = "humor comedia monólogo"
        ),
        EventoMock(
            id = 3,
            imagen = "imagen3",
            titulo = "JOSÉ ELÍAS EN ALICANTE-FÓRUM DAC",
            fecha = "Thursday, December 5 · 6:30 - 9pm CET",
            lugar = "VB Spaces",
            realizado = "By ACADEMIA DALE AL COCO SL",
            precio = 39.4f,
            seguidores = 0,
            tipo = "charlas conferencias forum empresa"
        ),
        EventoMock(
            id = 4,
            imagen = "imagen4",
            titulo = "GABRIEL FAURÉ (100 Años de Inspiradora Trascendencia)",
            fecha = "jue, 7 nov 2024 20:30 - 22:00 CET",
            lugar = "Real Liceo Casino de Alicante",
            realizado = "By Orquesta de Cámara VIRTUÓS MEDITERRANI",
            precio = 22.45f,
            seguidores = 122,
            tipo = "orquesta musica clasica"
        ),
        EventoMock(
            id = 5,
            imagen = "imagen5",
            titulo = "Alicante Business: The Science Of Sales & Marketing - For Beginners!",
            fecha = "Tuesday, November 5 · 7 - 9:30pm CET",
            lugar = "VB Spaces",
            realizado = "By Coach Michael Lin",
            precio = 55.19f,
            seguidores = 12854,
            tipo = "charlas conferencias forum empresa"
        ),
    )

    suspend fun get(): List<EventoMock> = coroutineScope { listadoEventosMock }
}