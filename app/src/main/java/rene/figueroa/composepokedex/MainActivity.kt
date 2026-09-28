package rene.figueroa.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import rene.figueroa.composepokedex.components.Ability
import rene.figueroa.composepokedex.components.Chip
import rene.figueroa.composepokedex.domain.Pokemon
import rene.figueroa.composepokedex.dummies.pokemonList
import rene.figueroa.composepokedex.ui.theme.ComposePokedexTheme
import rene.figueroa.composepokedex.ui.theme.ElectricYellow
import rene.figueroa.composepokedex.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val pokemon = pokemonList[3]

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ElectricYellow)
    ) {
        Image(
            painter = painterResource(R.drawable.pokeball),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopEnd)
                .offset(x = 50.dp, y = (-20).dp),
            alpha = 0.15f)

        Column(modifier = Modifier.fillMaxSize()) {
            PokemonHeader(
                pokemon.name,
                pokemon.number,
                pokemon.fav)

            Spacer(modifier = Modifier.height(70.dp))

            PokemonCard()
        }
    }
}

@Composable
fun PokemonHeader(name: String, number: Int, fav: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 32.dp, end = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Text(
                text = name,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Text(
                text = "#${number.toString().padStart(3, '0')}", // Formato #025
                color = Color.DarkGray,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.End))
        }
        Image(
            painter = painterResource(if (fav) R.drawable.star_filled else R.drawable.star_outline), // Asegúrate de tener ic_star
            contentDescription = "Favorito",
            modifier = Modifier.size(40.dp))
    }
}

@Composable
fun PokemonCard() {

    val pokemon = pokemonList[3]

    Box(
        contentAlignment = Alignment.TopCenter
    ) {

        Image(
            painter = painterResource(pokemon.image),
            contentDescription = null,
            modifier = Modifier
                .offset(y = (-70).dp)
                .size(170.dp)
                .zIndex(2f))

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White),
            elevation = CardDefaults.cardElevation(10.dp)) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(90.dp))

                Chip(
                    pokemon.type,
                    ElectricYellow
                )

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Ability("row", "Altura", "  ${pokemon.height} m")
                        Ability("row", "Peso", "  ${pokemon.weight} kg")
                    }
                    Ability("column", "Habilidad", pokemon.ability)
                }

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = pokemon.description,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(R.drawable.arbok),
                            contentDescription = null,
                            modifier = Modifier.size(90.dp)
                        )

                        Text("Arbok N.º 0024")
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(R.drawable.raichu),
                            contentDescription = null,
                            modifier = Modifier.size(90.dp)
                        )

                        Text("Raichu N.º 0026")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPokemon() {

    ComposePokedexTheme {
        Greeting()
    }
}
