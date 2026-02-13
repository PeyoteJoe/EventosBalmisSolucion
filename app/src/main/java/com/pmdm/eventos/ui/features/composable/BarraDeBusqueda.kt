package com.pmdm.eventos.ui.features.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeBusqueda(
    query: String,
    suggestion: List<String>,
    onSearchActive: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit
) {
    var expandible by rememberSaveable { mutableStateOf(false) }
    var suggestionBusqueda by remember { mutableStateOf(suggestion) }

    Box(
        Modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.Center)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = {
                        onQueryChange(it)
                        if (it != "") suggestionBusqueda =
                            suggestion.filter { e -> e.lowercase().contains(it.lowercase()) }
                        else suggestionBusqueda = suggestion
                    },

                    onSearch = { expandible = false },
                    expanded = expandible,
                    onExpandedChange = {
                        expandible = it
                        onSearchActive(true)
                    },
                    placeholder = { Text("Introduce opción") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onSearchActive(false)
                            })
                    },
                )
            },

            expanded = expandible,
            onExpandedChange = { expandible = it },
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                repeat(suggestionBusqueda.size) { i ->
                    val resultText = suggestionBusqueda[i]
                    ListItem(
                        headlineContent = { Text(resultText) },
                        supportingContent = { Text("Additional info") },
                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier =
                        Modifier
                            .clickable {
                                onQueryChange(resultText)
                                onSearchActive(false)
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}


@Composable
@Preview

fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    var suggestion: List<String> =
        remember { listOf("Musicales", "Conciertos", "Música en directo", "Teatro") }

    BarraDeBusqueda(
        query = query,
        suggestion = suggestion,
        onQueryChange = { query = it },
        onSearchActive = { })
}

