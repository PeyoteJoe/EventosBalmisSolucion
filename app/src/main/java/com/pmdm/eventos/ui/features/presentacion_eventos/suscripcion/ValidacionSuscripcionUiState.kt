package com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion

import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.ValidacionCompuesta


data class ValidacionSuscripcionUiState(
    val validacionCorreo: Validacion = object : Validacion {},
) : Validacion {
    private lateinit var validacionCompuesta: ValidacionCompuesta

    private fun componerValidacion(): ValidacionCompuesta {
        validacionCompuesta = ValidacionCompuesta()
            .add(validacionCorreo)
        return validacionCompuesta
    }

    override val hayError: Boolean
        get() = componerValidacion().hayError
    override val mensajeError: String?
        get() = validacionCompuesta.mensajeError
}