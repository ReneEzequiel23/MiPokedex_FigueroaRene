package rene.figueroa.composepokedex.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rene.figueroa.composepokedex.ui.theme.ComposePokedexTheme
import rene.figueroa.composepokedex.domain.Pokemon
import rene.figueroa.composepokedex.dummies.pokemonList

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, innerPadding: PaddingValues) {
    LazyColumn(contentPadding = innerPadding) {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuPokedexPreview() {
    ComposePokedexTheme {
        MenuPokedex(
            pokemonList = pokemonList,
             innerPadding = PaddingValues(0.dp)
        )
    }
}