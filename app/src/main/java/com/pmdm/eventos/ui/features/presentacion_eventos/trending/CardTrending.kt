package com.pmdm.eventos.ui.features.presentacion_eventos.trending

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoUiState
import com.pmdm.eventos.ui.theme.PresentacionEventosTheme

@Composable
fun CardTrending(eventoUiState: EventoUiState, onNavigateToInformacionTrending: (Int) -> Unit) {

    ElevatedCard(
        modifier = Modifier.clickable{onNavigateToInformacionTrending(eventoUiState.id)},
        shape = CardDefaults.outlinedShape,
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp).fillMaxWidth()
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
                    modifier = Modifier.weight(0.8f),
                    text = eventoUiState.realizado, style = TextStyle(
                        fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                )
                IconButton(modifier = Modifier
                    .background(
                        color = Color(0x88770B0B), shape = MaterialTheme.shapes.extraLarge
                    )
                    .weight(0.2f), enabled = false, onClick = {}) {
                    Text(
                        color = Color.White,
                        text = eventoUiState.precio.toString() + "€", style = TextStyle(
                            fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardTrendingPreView()
{
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

            CardTrending(
                eventoUiState = eventosUiState,{}
            )

    }

}