package com.pmdm.eventos.ui.features.presentacion_eventos

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.eventos.R

import com.pmdm.eventos.ui.features.composable.BarraDeBusqueda
import com.pmdm.eventos.ui.navigation.EventosNavHost
import com.pmdm.eventos.ui.navigation.InformacionTrendingRoute
import com.pmdm.eventos.ui.navigation.ListadoEventoRoute
import com.pmdm.eventos.ui.navigation.SuscriptionRoute
import com.pmdm.eventos.ui.navigation.TrendingRoute

private fun iOpcionNevagacionSeleccionadaAPartirDeDestino(destino: NavDestination?): Int {
    return when {
        destino == null -> 0
        destino.hasRoute<ListadoEventoRoute>() -> 0
        destino.hasRoute<TrendingRoute>() or destino.hasRoute<InformacionTrendingRoute>() -> 1
        destino.hasRoute<SuscriptionRoute>() -> 2
        else -> 0
    }
}


@Composable
fun EventosScreen(
    query: String,
    suggestion: List<String>,
    onQueryChange: (String) -> Unit,
    suscrito: Boolean,
    onSuscrito: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController(),
) {
    var mostrarSearchBar by remember { mutableStateOf(false) }

    val entradaEnPilaDeNavegacionActuasState by navController.currentBackStackEntryAsState()
    val iOpcionNevagacionSeleccionada by remember {
        derivedStateOf {
            onQueryChange("")
            iOpcionNevagacionSeleccionadaAPartirDeDestino(
                destino = entradaEnPilaDeNavegacionActuasState?.destination
            )
        }
    }

    Scaffold(
        topBar = {
            if (!mostrarSearchBar) {
                BarraSuperior(
                    indexScreenState = iOpcionNevagacionSeleccionada,
                    onMostrarSearchBar = { mostrarSearchBar = true },
                    suscrito = suscrito,
                    navController = navController
                )
            }
        },
        bottomBar = {
            BarraNavegacion(indexScreenState = iOpcionNevagacionSeleccionada)
            {
                when (it) {
                    0 -> navController.navigate(ListadoEventoRoute)
                    1 -> navController.navigate(TrendingRoute)
                    2 -> navController.navigate(SuscriptionRoute)
                }
            }
        }
    ) { innerPading ->
        Column(modifier = Modifier.padding(innerPading))
        {
            if (mostrarSearchBar) {
                BarraDeBusqueda(
                    query = query,
                    suggestion = suggestion,
                    onQueryChange = onQueryChange,
                    onSearchActive = { mostrarSearchBar = it })
            }
            EventosNavHost(
                query = query,
                navController = navController,
                suscrito = suscrito,
                onSuscrito = onSuscrito
            )
        }
    }

}

@Composable
fun BarraNavegacion(
    indexScreenState: Int,
    onNavigateToScreen: (Int) -> Unit
) {
    data class IconosNavegacion(
        val icon: ImageVector,
        val title: String
    )

    val iconosNavegacion = listOf(
        IconosNavegacion(
            icon = ImageVector.vectorResource(R.drawable.home),
            title = "Eventos"
        ),
        IconosNavegacion(
            icon = ImageVector.vectorResource(R.drawable.trending),
            title = "Trending"
        ),
        IconosNavegacion(
            icon = ImageVector.vectorResource(R.drawable.subscribe),
            title = "Subcription"
        )
    )

    NavigationBar {
        iconosNavegacion.forEachIndexed { index, (icon, title) ->
            NavigationBarItem(
                icon = { Icon(painter = rememberVectorPainter(icon), contentDescription = title) },
                label = { Text(title) },
                selected = indexScreenState == index,
                onClick = { onNavigateToScreen(index) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    indexScreenState: Int,
    onMostrarSearchBar: (Boolean) -> Unit,
    suscrito: Boolean,
    navController: NavHostController
) {
    val contexto = LocalContext.current as Activity
    var mostrarMenu by remember {
        mutableStateOf(false)
    }
    Box()
    {
        TopAppBar(
            title = {
                Logo(suscrito = suscrito)
            },
            navigationIcon = {
                Icon(
                    painter = Filled.getArrowBackIosIcon(),
                    contentDescription = "Volver",
                    modifier = Modifier.clickable { navController.navigateUp() })
            },
            actions = {
                if (indexScreenState == 0) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Búsqueda",
                        modifier = Modifier.clickable { onMostrarSearchBar(true) })
                }
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menú",
                    modifier = Modifier.clickable { mostrarMenu = !mostrarMenu })

                MenuDesplegable(
                    mostrarMenu = mostrarMenu,
                    onMostrarMenu = { mostrarMenu = it },
                    onClickSalir = { contexto.finish() }
                )

            }
        )
    }
}

@Composable
fun MenuDesplegable(
    mostrarMenu: Boolean,
    onMostrarMenu: (Boolean) -> Unit,
    onClickSalir: () -> Unit
) {
    DropdownMenu(
        expanded = mostrarMenu,
        onDismissRequest = { onMostrarMenu(false) }) {

        DropdownMenuItem(text = { Text("Salir Aplicación") }, onClick = {
            onMostrarMenu(false)
            onClickSalir()
        })
    }
}


@Composable
fun Logo(modifier: Modifier = Modifier, suscrito: Boolean) {
    Box(modifier = modifier) {

        Image(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .size(width = 110.dp, height = 80.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth
        )
        if (suscrito) Image(
            modifier = Modifier
                .padding(9.dp)
                .align(alignment = Alignment.TopEnd),
            painter = painterResource(R.drawable.insignia),
            contentDescription = "Menu",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@Preview
fun EventosScreenPreview() {

    var query by remember { mutableStateOf("") }
    val suggestion: List<String> = remember {
        listOf("Musicales", "Conciertos", "Música en directo", "Teatro")
    }

    EventosScreen(
        query = query,
        suggestion = suggestion,
        onQueryChange = { query = it },
        suscrito = false,
        onSuscrito = {},
        navController = rememberNavController()
    )
}