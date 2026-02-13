package com.pmdm.eventos.ui.features.presentacion_eventos.suscripcion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldEmail
import com.pmdm.eventos.ui.features.composable.RadioButtonPago
import com.pmdm.eventos.ui.theme.PresentacionEventosTheme


@Composable
fun SuscripcionScreen(
    suscripcionUiState: SuscripcionUiState,
    validacionSuscripcionUiState: ValidacionSuscripcionUiState,
    onSuscrito: (Boolean) -> Unit,
    onSuscripcionEvent: (SuscripcionEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suscripcionUiState.nombre,
            onValueChange = { onSuscripcionEvent(SuscripcionEvent.OnCambiaNombre(it)) },
            label = { Text("Nombre") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suscripcionUiState.nick,
            onValueChange = { onSuscripcionEvent(SuscripcionEvent.OnCambiaNick(it)) },
            label = { Text("Nick") })

        OutlinedTextFieldEmail(
            modifier = Modifier.fillMaxWidth(),
            label = "Correo",
            emailState = suscripcionUiState.correo,
            validacionState = validacionSuscripcionUiState.validacionCorreo,
            onValueChange = { onSuscripcionEvent(SuscripcionEvent.OnCambiaCorreo(it)) })
        HorizontalDivider()
        MetodoPago(
            metodoPago = suscripcionUiState.metodoPago,
            onSuscripcionEvent = onSuscripcionEvent
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .weight(3f), onClick = {
                    if (!suscripcionUiState.metodoPago.isEmpty()) {
                        onSuscripcionEvent(SuscripcionEvent.OnAceptaSuscripcion)
                        onSuscrito(true)
                    }
                }) {
                Text(text = "Suscribirse")
            }
            Spacer(modifier = Modifier.weight(0.5f))
            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .weight(3f),
                onClick = {
                    onSuscripcionEvent(SuscripcionEvent.OnCancelaSuscripcion)
                    onSuscrito(false)
                }) {
                    Text(text = "Rechazar")
            }
        }
    }
}

@Composable
fun MetodoPago(
    metodoPago: String, onSuscripcionEvent: (SuscripcionEvent) -> Unit
) {

    Column {
        RadioButtonPago(
            metodoPago,
            onOptionSelected = { onSuscripcionEvent(SuscripcionEvent.OnCambiaMetodoPago(it)) })
        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(2f))
            Text(
                metodoPago.uppercase(),
                modifier = Modifier.weight(4f),
                textAlign = TextAlign.Center
            )
            HorizontalDivider(modifier = Modifier.weight(2f))
        }
    }
}


@Preview
@Composable
fun SuscripcionScreenPreview() {
    PresentacionEventosTheme {

        Surface() {
            var subcriptor by remember {
                mutableStateOf(
                    SuscripcionUiState(
                        id = 0,
                        nick = "Ana",
                        nombre = "Ana Garcia",
                        correo = "ana@gmail.com",
                        metodoPago = "PayPal",
                    )
                )
            }

            val validacionSuscripcionUiState = remember { ValidacionSuscripcionUiState() }

            SuscripcionScreen(subcriptor, validacionSuscripcionUiState, {}, {
                when (it) {
                    is SuscripcionEvent.OnCambiaNombre -> subcriptor =
                        subcriptor.copy(nombre = it.nombre)

                    is SuscripcionEvent.OnCambiaNick -> subcriptor = subcriptor.copy(nick = it.nick)
                    is SuscripcionEvent.OnCambiaCorreo -> subcriptor =
                        subcriptor.copy(correo = it.correo)

                    is SuscripcionEvent.OnCambiaMetodoPago -> subcriptor =
                        subcriptor.copy(metodoPago = it.metodoPago)

                    is SuscripcionEvent.OnAceptaSuscripcion -> {}

                    is SuscripcionEvent.OnCancelaSuscripcion -> {}

                }

            })
        }
    }
}