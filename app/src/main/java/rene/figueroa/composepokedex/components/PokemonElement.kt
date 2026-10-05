package rene.figueroa.composepokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rene.figueroa.composepokedex.domain.Pokemon
import rene.figueroa.composepokedex.ui.theme.Green
import androidx.compose.ui.tooling.preview.Preview
import rene.figueroa.composepokedex.ui.theme.ComposePokedexTheme
import rene.figueroa.composepokedex.R

@Composable
fun PokemonRow(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        // Imagen del pokemon
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = pokemon.description,
                fontSize = 10.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Height: ${pokemon.height}", style = MaterialTheme.typography.labelMedium)
                Text(text = "Weight: ${pokemon.weight}", style = MaterialTheme.typography.labelMedium)
            }
        }

        Text(
            text = pokemon.number.toString(),
            modifier = Modifier
                .background(Green)
                .padding(horizontal = 5.dp, vertical = 2.dp),
            color = androidx.compose.ui.graphics.Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonRowPreview() {
    ComposePokedexTheme {
        val dummyPokemon = Pokemon(
            name = "Bulbasaur",
            number = 1,
            type = "Grass/Poison",
            description = "There is a plant seed on its back from the day this Pokémon is born.",
            height = 0.7f,
            weight = 6.9f,
            fav = false,
            ability = "Overgrow",
            image = R.drawable.pikachu
        )

        PokemonRow(pokemon = dummyPokemon)
    }
}