package com.pmdm.eventos.data.mocks.suscripcion

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuscripcionDaoMock @Inject constructor() {

    private val listadoSuscriptores = mutableListOf(
        SuscripcionMock(
            id = 0,
            nick = "Pep",
            nombre = "Jose García",
            correo = "jose@gmail.com",
            metodoPago = "PayPal",
            suscrito = false
        )
    )

    fun get(): List<SuscripcionMock> = listadoSuscriptores
}



