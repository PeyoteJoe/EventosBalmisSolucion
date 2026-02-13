package com.pmdm.eventos.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.EventosScreen

import com.pmdm.eventos.ui.theme.PresentacionEventosTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileInputStream

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PresentacionEventosTheme {
                val eventoViewModel: EventoViewModel = hiltViewModel()
                EventosScreen(
                    query = eventoViewModel.query,
                    suggestion = eventoViewModel.suggestion,
                    onQueryChange = eventoViewModel::onQueryChange,
                    suscrito = eventoViewModel.suscrito,
                    onSuscrito = eventoViewModel::onSuscrito
                )
            }
        }
    }



}

