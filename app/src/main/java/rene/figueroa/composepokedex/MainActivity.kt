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
fun PokemonHeader(name:String, number:Int, fav:Boolean){
    Row(Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            Text(name)
            Text(
                "${number}",
                modifier = Modifier.align(Alignment.End)
            )
        }
        Box(){
            Image(
                painter = painterResource(R.drawable.pokeball),
                contentDescription = "Pokeball image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(130.dp).offset(30.dp, 45.dp)
            )
            Image(
                painter = painterResource(
                    if(fav) R.drawable.star_filled else R.drawable.star_outline
                ),
                contentDescription =
                    if(fav) "Yellow star filled" else "yellow star outlined",

                )
        }
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
                .zIndex(2f)
        )

        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(90.dp))

                Chip(
                    pokemon.type,
                    ElectricYellow
                )

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Ability(
                        "column",
                        "Altura",
                        "${pokemon.height} m"
                    )

                    Ability(
                        "column",
                        "Peso",
                        "${pokemon.weight} kg"
                    )

                    Ability(
                        "column",
                        "Habilidad",
                        pokemon.ability
                    )
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

@Composable
fun Greeting(
    modifier: Modifier = Modifier
) {

    val pokemon = pokemonList[3]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElectricYellow)
    ) {

        PokemonHeader(
            pokemon.name,
            pokemon.number,
            pokemon.fav
        )

        PokemonCard()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemon() {

    ComposePokedexTheme {
        Greeting()
    }
}
