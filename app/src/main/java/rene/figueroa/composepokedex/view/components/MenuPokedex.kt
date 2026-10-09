package rene.figueroa.composepokedex.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rene.figueroa.composepokedex.ui.theme.ComposePokedexTheme
import rene.figueroa.composepokedex.model.domain.Pokemon
import rene.figueroa.composepokedex.model.data.pokemonList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>,onNavigateToDetail: (Int) -> Unit) {
    LazyColumn{
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon = pokemon, onNavigateToDetail = onNavigateToDetail)}
    }
}

@Composable
fun FavoritesRow(favoriteList: List<Pokemon>,onNavigateToDetail: (Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(favoriteList) { pokemon ->
            FavoritePokemon(pokemon = pokemon, onNavigateToDetail = onNavigateToDetail)
        }
    }
}

@Composable
fun PokedexGrid(pokemonList: List<Pokemon>,onNavigateToDetail: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonCell(pokemon = pokemon, onNavigateToDetail = onNavigateToDetail)
        }
    }
}