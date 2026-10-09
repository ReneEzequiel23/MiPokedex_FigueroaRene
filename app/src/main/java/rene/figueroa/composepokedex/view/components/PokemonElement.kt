package rene.figueroa.composepokedex.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rene.figueroa.composepokedex.model.domain.Pokemon
import androidx.compose.ui.tooling.preview.Preview
import rene.figueroa.composepokedex.ui.theme.ComposePokedexTheme
import rene.figueroa.composepokedex.R
import rene.figueroa.composepokedex.utilities.getColorByType
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import rene.figueroa.composepokedex.ui.theme.OffWhite

@Composable
fun PokemonRow(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToDetail(pokemon.number) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
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

        NumberChip(
            text = pokemon.number.toString(),
            colors = getColorByType(pokemon.type)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    val colors = getColorByType(pokemon.type)

    Column(
        modifier = Modifier
            .clickable { onNavigateToDetail(pokemon.number) }
            .padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(
                        width = 5.dp,
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                colors.first,
                                OffWhite,
                                colors.first,
                                OffWhite,
                                colors.first
                            )
                        ),
                        shape = RectangleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .width(75.dp)
                        .padding(5.dp)
                )
            }
            NumberChip(text = pokemon.number.toString(), colors = colors)
        }

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    val colors = getColorByType(pokemon.type)

    Column(
        modifier = Modifier.clickable { onNavigateToDetail(pokemon.number) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(id = pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            NumberChip(
                text = pokemon.number.toString(),
                colors = colors,
                modifier = Modifier.padding(top = 10.dp, end = 10.dp)
            )
        }
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
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

        PokemonRow(pokemon = dummyPokemon, onNavigateToDetail = {})
    }
}