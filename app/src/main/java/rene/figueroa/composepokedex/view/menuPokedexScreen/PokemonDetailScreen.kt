package rene.figueroa.composepokedex.view.menuPokedexScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import rene.figueroa.composepokedex.model.data.pokemonList
import rene.figueroa.composepokedex.model.domain.Pokemon

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Int){
    val pokemon = getPokemonByNumber(pokemon)
    Column(Modifier.padding(innerPadding)) {
        Text(pokemon.name)
        Image(painterResource(pokemon.image),
            contentDescription = "{$pokemon.name} image")
    }
}

fun getPokemonByNumber(pokemon: Int):Pokemon{
    if (pokemon == -1){

    }
    return pokemonList.filter {
        it.number == pokemon
    }.first()
}

fun getFavoritePokemons(): List<Pokemon>{
    return pokemonList.filter {
        it.fav
    }
}
