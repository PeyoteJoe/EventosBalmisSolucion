package com.pmdm.eventos.ui.features.presentacion_eventos.trending

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoUiState
import com.pmdm.eventos.ui.theme.PresentacionEventosTheme

@Composable
fun TrendingScreen(
    modifier: Modifier = Modifier,
    listaEventos: List<EventoUiState>,
    onNavigateToInformacionTrending: (Int) -> Unit
) {
    LazyColumn(modifier.padding(5.dp)) {
        this.items(count = listaEventos.size,
            key = { listaEventos[it].id })
        {
            CardTrending(listaEventos[it], onNavigateToInformacionTrending)
            Spacer(modifier=Modifier.height(2.dp))
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TrendinScreenPreview() {
    PresentacionEventosTheme {
        val listadoEventos = mutableListOf(
            EventoUiState(
                id = 1,
                imagen = "imagen1",
                titulo = "VERMOOD OPEN BAR SAUSALITO",
                fecha = "Saturday, November 16 · 2 - 4pm CET",
                lugar = "Sausalito Premium Club by Puerto",
                realizado = "By SAUSALITO PREMUIM CLUB by Puerto",
                precio = 10f,
                seguidores = 23,
            ),
            EventoUiState(
                id = 2,
                imagen = "imagen2",
                titulo = "English Stand-up Comedy in Alicante: We are NOT From Here!",
                fecha = "Sunday, November 10 · 6 - 7:30pm CET",
                lugar = "El Refugio café art nature - arte - bar",
                realizado = "By Madrid Comedy Lab",
                precio = 5.77f,
                seguidores = 57,
            ),
            EventoUiState(
                id = 3,
                imagen = "imagen3",
                titulo = "JOSÉ ELÍAS EN ALICANTE-FÓRUM DAC",
                fecha = "Thursday, December 5 · 6:30 - 9pm CET",
                lugar = "VB Spaces",
                realizado = "By ACADEMIA DALE AL COCO SL",
                precio = 39.4f
            ),
            EventoUiState(
                id = 4,
                imagen = "imagen4",
                titulo = "GABRIEL FAURÉ (100 Años de Inspiradora Trascendencia)",
                fecha = "jue, 7 nov 2024 20:30 - 22:00 CET",
                lugar = "Real Liceo Casino de Alicante",
                realizado = "By Orquesta de Cámara VIRTUÓS MEDITERRANI",
                precio = 22.45f,
                seguidores = 122,
            ),
            EventoUiState(
                id = 5,
                imagen = "imagen5",
                titulo = "Alicante Business: The Science Of Sales & Marketing - For Beginners!",
                fecha = "Tuesday, November 5 · 7 - 9:30pm CET",
                lugar = "VB Spaces",
                realizado = "By Coach Michael Lin",
                precio = 55.19f,
                seguidores = 12854,
            ),
        )
        Scaffold (){ innerPadding ->

            TrendingScreen(
                modifier = Modifier.padding(innerPadding),
                listaEventos = listadoEventos,
                {}
            )
        }
    }
}