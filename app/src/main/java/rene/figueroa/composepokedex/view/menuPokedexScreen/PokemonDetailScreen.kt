package rene.figueroa.composepokedex.view.menuPokedexScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import rene.figueroa.composepokedex.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemonNumber: Int, pokemonDetailViewModel: PokemonDetailViewModel) {
    val pokemon = pokemonDetailViewModel.getPokemon(pokemonNumber) ?: return
    val companion by pokemonDetailViewModel.companion.collectAsState()
    val isCompanion = companion.number == pokemon.number
    Column(modifier = Modifier.padding(innerPadding)) {
        Text(pokemon.name)
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image"
        )
        if (isCompanion) {
            Text("⭐ Es tu compañero")
        } else {
            Button({ pokemonDetailViewModel.saveCompanion(pokemonNumber) }) {
                Text("Seleccionar compañero")
            }
        }
        if (pokemonDetailViewModel.selectionCompleted && isCompanion) {
            Text("Compañero seleccionado.")
        }
    }
}
