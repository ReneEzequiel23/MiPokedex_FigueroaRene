package rene.figueroa.composepokedex.view.menuPokedexScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import rene.figueroa.composepokedex.model.domain.pokemonTypes
import rene.figueroa.composepokedex.ui.theme.Typography
import rene.figueroa.composepokedex.viewmodel.AddPokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues,
    addPokemonViewModel: AddPokemonViewModel
) {
    val number = remember { TextFieldState() }
    val name = remember { TextFieldState() }
    val description = remember { TextFieldState() }
    var openType by remember { mutableStateOf(false) }
    val type = remember { TextFieldState(pokemonTypes[0]) }
    var inputError by remember { mutableStateOf<String?>(null) }
    val pokemonList by addPokemonViewModel.pokemons.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text("Agregar pokemon", style = Typography.titleLarge)

        Spacer(Modifier.size(20.dp))

        TextField(
            state = number,
            label = { Text("Número de pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            state = name,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = openType,
            onExpandedChange = { openType = it }
        ) {
            OutlinedTextField(
                readOnly = true,
                label = { Text("Tipo") },
                state = type,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType)
                },
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = openType,
                onDismissRequest = { openType = false }
            ) {
                pokemonTypes.forEach { pokemonType ->
                    DropdownMenuItem(
                        text = { Text(pokemonType) },
                        onClick = {
                            type.setTextAndPlaceCursorAtEnd(pokemonType)
                            openType = false
                        }
                    )
                }
            }
        }

        TextField(
            state = description,
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        inputError?.let { Text(it, color = androidx.compose.material3.MaterialTheme.colorScheme.error) }
        addPokemonViewModel.errorMessage?.let {
            Text(it, color = androidx.compose.material3.MaterialTheme.colorScheme.error)
        }
        if (addPokemonViewModel.pokemonSaved) Text("Pokemon guardado")

        Button(onClick = {
            val pokemonNumber = number.text.toString().toIntOrNull()
            inputError = when {
                pokemonNumber == null || pokemonNumber <= 0 -> "Ingresa un número válido"
                name.text.toString().isBlank() -> "Ingresa el nombre del pokemon"
                description.text.toString().isBlank() -> "Ingresa la descripción"
                else -> null
            }

            if (inputError == null) {
                addPokemonViewModel.savePokemon(
                    name = name.text.toString(),
                    type = type.text.toString(),
                    number = pokemonNumber!!,
                    description = description.text.toString()
                )
            }
        }) {
            Text("Agregar pokemon")
        }

        Text("Pokemones guardados", style = Typography.titleLarge)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList) { pokemon ->
                Text(pokemon.toString())
            }
        }
    }
}
