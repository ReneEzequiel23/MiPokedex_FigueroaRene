package rene.figueroa.composepokedex.screems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rene.figueroa.composepokedex.components.PokemonGridItem
import rene.figueroa.composepokedex.domain.Pokemon
import rene.figueroa.composepokedex.dummies.showPokemonList

@Composable
fun PokedexMenuScreem(pokemons: List<Pokemon>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(5.dp,5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(pokemons){
            pokemon ->
            PokemonGridItem(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexMenuScreenPreview(){
    PokedexMenuScreem(showPokemonList())
}