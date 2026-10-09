package rene.figueroa.composepokedex.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rene.figueroa.composepokedex.view.components.FavoritesRow
import rene.figueroa.composepokedex.view.components.PokedexGrid
import rene.figueroa.composepokedex.model.data.pokemonList
import rene.figueroa.composepokedex.ui.theme.Blue
import rene.figueroa.composepokedex.ui.theme.Green
import rene.figueroa.composepokedex.ui.theme.LightBlue
import rene.figueroa.composepokedex.ui.theme.LightGreen
import rene.figueroa.composepokedex.view.components.MenuPokedex
import androidx.compose.runtime.*
import rene.figueroa.composepokedex.R
@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onNavigateToDetail: (pokemon: Int) -> Unit
) {
    var grid by remember { mutableStateOf(false) }
    val favList = pokemonList.filter { it.fav }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mis Favoritos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Switch(
                checked = grid,
                onCheckedChange = { grid = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Green,
                    checkedTrackColor = LightGreen,
                    uncheckedThumbColor = Blue,
                    uncheckedTrackColor = LightBlue,
                    uncheckedBorderColor = Color.Transparent
                ),
                thumbContent = {
                    if (grid) {
                        Icon(
                            painter = painterResource(R.drawable.puntos),
                            contentDescription = "grid icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.lista),
                            contentDescription = "list icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                }
            )
        }

        FavoritesRow(
            favoriteList = favList,
            onNavigateToDetail = onNavigateToDetail
        )

        Text(
            text = "Todos mis pokemones",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 10.dp)
        )

        if (grid) {
            PokedexGrid(
                pokemonList = pokemonList,
                onNavigateToDetail = onNavigateToDetail
            )
        } else {
            MenuPokedex(
                pokemonList = pokemonList,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}