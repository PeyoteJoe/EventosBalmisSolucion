package com.pmdm.eventos.ui.features.presentacion_eventos.trending

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.pmdm.eventos.R
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoUiState
import com.pmdm.eventos.ui.theme.PresentacionEventosTheme

@Composable
fun InformacionTrendingScreen(
    modifier: Modifier = Modifier,
    eventosUiState: EventoUiState,
) {
    Column(
        modifier = modifier.then(
            Modifier.padding(8.dp)
            //  .fillMaxSize()
        )
    ) {


        ImagenEvento(eventosUiState = eventosUiState)
        LineaSeguidores(eventosUiState)
        CuerpoInformacion(eventoUiState = eventosUiState)

    }
}

@Composable
fun CuerpoInformacion(
    eventoUiState: EventoUiState,

    ) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(
            text = eventoUiState.titulo, style = TextStyle(
                fontSize = TextUnit(value = 20f, type = TextUnitType.Sp),
                letterSpacing = TextUnit(value = 4f, type = TextUnitType.Sp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            ), color = Color(0xAA880000)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                modifier = Modifier.weight(0.8f), text = eventoUiState.realizado, style = TextStyle(
                    fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
            IconButton(modifier = Modifier
                .background(
                    color = Color(0xFF770B0B), shape = MaterialTheme.shapes.small
                )
                .weight(0.2f), enabled = false, onClick = {}) {
                Text(
                    color = Color.White,
                    text = eventoUiState.precio.toString() + "€",
                    style = TextStyle(
                        fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                )
            }
        }
        TextoInformacion("Fecha y Hora", Icons.Filled.DateRange, eventoUiState.fecha)
        TextoInformacion("Ubicación", Icons.Filled.LocationOn, eventoUiState.lugar)
    }/*  if (dialogoUiState.dialogoVisible)
          DialogoInscripcion(dialogoUiState, onDialogoEvent)
      if (dialogoUiState.dialogoComprobacionVisible)
          DialogoConfirmacionInscripcion(dialogoUiState, onDialogoEvent)*/

}

@Composable
fun LineaSeguidores(eventosUiState: EventoUiState) {
    Row {
        Image(
            modifier = Modifier
                .size(15.dp)
                .weight(0.1f)
                .align(alignment = Alignment.CenterVertically),
            imageVector = Icons.Filled.Person,
            contentDescription = "seguidores"
        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(0.7f),
            text = "Seguidores  ${eventosUiState.seguidores}",
            style = TextStyle(
                fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )
    }
}

@Composable
private fun TextoInformacion(titulo: String, icono: ImageVector, detalle: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = titulo, style = TextStyle(
            fontSize = TextUnit(value = 18f, type = TextUnitType.Sp),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    Row {
        Image(
            modifier = Modifier.size(15.dp), imageVector = icono, contentDescription = titulo
        )
        Text(
            text = detalle, style = TextStyle(
                fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )
    }
}

private fun imageResource(imagen: String?) = when (imagen) {
    "imagen1" -> R.drawable.imagen1
    "imagen2" -> R.drawable.imagen2
    "imagen3" -> R.drawable.imagen3
    "imagen4" -> R.drawable.imagen4
    else -> R.drawable.imagen5
}


@Composable
fun ImagenEvento(eventosUiState: EventoUiState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageResource(eventosUiState.imagen)),
            contentDescription = "Imagen de evento",
            contentScale = ContentScale.Crop
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun InformacionTrendinScreenPreview() {
    PresentacionEventosTheme {
        val eventosUiState = EventoUiState(
            id = 1,
            imagen = "imagen1",
            titulo = "VERMOOD OPEN BAR SAUSALITO",
            fecha = "Saturday, November 16 · 2 - 4pm CET",
            lugar = "Sausalito Premium Club by Puerto",
            realizado = "By SAUSALITO PREMUIM CLUB by Puerto",
            precio = 10f,
            seguidores = 23,
        )

        Scaffold() { innerPadding ->

            InformacionTrendingScreen(
                modifier = Modifier.padding(innerPadding),
                eventosUiState = eventosUiState,
            )
        }
    }
}