package com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion


import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorCorreo
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio
import javax.inject.Inject

class ValidadorSuscripcion  @Inject constructor() : Validador<SuscripcionUiState> {
    var validadorCorreo =
        ValidadorCompuesto<String>()
            .add(ValidadorTextoNoVacio("El correo no puede estar vacío"))
            .add(ValidadorCorreo("El correo no es válido"))


    override fun valida(datos: SuscripcionUiState): ValidacionSuscripcionUiState {
        val validacionLogin = validadorCorreo.valida(datos.correo)

        return ValidacionSuscripcionUiState(
            validacionCorreo = validacionLogin,
        )
    }
}