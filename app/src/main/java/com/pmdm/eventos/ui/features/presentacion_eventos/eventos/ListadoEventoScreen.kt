package com.pmdm.eventos.ui.features.presentacion_eventos.eventos

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ListadoEventosScreen(
    suscricion: Boolean,
    listaEventos: SnapshotStateList<EventoUiState>,
    onClickFavoritos: (Int) -> Unit
) {

    val lazyListState = rememberLazyListState()

    val scrollToInicio by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    LaunchedEffect(scrollToInicio) {
        lazyListState.animateScrollToItem(0)
    }

    LazyRow(
        state = lazyListState
    ) {
        this.items(count = listaEventos.size,
            key = { listaEventos[it].id })
        {
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .padding(8.dp)
            ) {
                ImagenEvento(eventosUiState = listaEventos[it], onClickFavoritos = onClickFavoritos)
                LineaSeguidores(listaEventos[it])
                CuerpoInformacion(suscricion = suscricion, eventoUiState = listaEventos[it])
            }
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun CuerpoInformacion(
    suscricion: Boolean,
    eventoUiState: EventoUiState
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
                modifier = Modifier.weight(0.8f),
                text = eventoUiState.realizado, style = TextStyle(
                    fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
            Box(modifier = Modifier.weight(0.2f).height(85.dp))
            {
                IconButton(modifier = Modifier.size(55.dp)
                    .background(
                        color = Color(0xFF770B0B), shape = MaterialTheme.shapes.small
                    ), enabled = false, onClick = {}) {
                    Text(
                        color = Color.White,
                        text = String.format("%.1f",eventoUiState.precio) + "€", style = TextStyle(
                            fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )

                }
                if(suscricion)
                IconButton(modifier = Modifier.align(Alignment.BottomEnd).size(55.dp)
                    .background(
                        color = Color(0xFF8BC34A), shape = MaterialTheme.shapes.small
                    )
                    , enabled = false, onClick = {}) {
                    Text(
                        color = Color.White,
                        text = String.format("%.1f",eventoUiState.precio*0.8f) + "€", style = TextStyle(
                            fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )

                }
            }
        }
        TextoInformacion("Fecha y Hora", Icons.Filled.DateRange, eventoUiState.fecha)
        TextoInformacion("Ubicación", Icons.Filled.LocationOn, eventoUiState.lugar)
    }
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
            text = "Seguidores  ${eventosUiState.seguidores}", style = TextStyle(
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
fun ImagenEvento(eventosUiState: EventoUiState, onClickFavoritos: (Int) -> Unit) {
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
        Row(
            modifier = Modifier.align(alignment = Alignment.BottomEnd)
        ) {
            IconButton(modifier = Modifier.background(
                color = Color(0xAA990000), shape = MaterialTheme.shapes.extraLarge
            ), onClick = { onClickFavoritos(eventosUiState.id) }) {
                var imagen =
                    if (eventosUiState.siguiendo) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                Image(
                    modifier = Modifier,
                    imageVector = imagen,
                    contentDescription = "Favorito"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(modifier = Modifier.background(
                color = Color(0xAA990000), shape = MaterialTheme.shapes.extraLarge
            ), onClick = {

            }) {

                Image(imageVector = Icons.Filled.Share, contentDescription = "Compartir")
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PresentacionEventoScreenPreview() {

    PresentacionEventosTheme {
        Surface {
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
            ListadoEventosScreen(
                listaEventos = listadoEventos.toMutableStateList(),
                suscricion = false,
                onClickFavoritos = {})
        }
    }
}