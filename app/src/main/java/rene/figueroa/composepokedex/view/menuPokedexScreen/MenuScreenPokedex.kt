package rene.figueroa.composepokedex.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rene.figueroa.composepokedex.model.data.getFavoritePokemons
import rene.figueroa.composepokedex.view.components.FavoritesRow
import rene.figueroa.composepokedex.view.components.PokedexGrid
import rene.figueroa.composepokedex.model.data.pokemonList
import rene.figueroa.composepokedex.R

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onNavigateToHunt: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Favorite Pokemons")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { onNavigateToHunt() }) {
                    Text("Catch New")
                }
                IconButton(onClick = { onNavigateToProfile() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Perfil"
                    )
                }
            }
        }

        FavoritesRow(getFavoritePokemons(), onNavigateToDetail)
        Spacer(Modifier.size(15.dp))
        Text("All pokemons")
        PokedexGrid(pokemonList, onNavigateToDetail)
    }
}

@Preview(showBackground = true)
@Composable
fun screenPreview() {
    MenuPokedexScreen(
        innerPadding = PaddingValues(10.dp, 15.dp),
        onNavigateToDetail = {},
        onNavigateToHunt = {},
        onNavigateToProfile = {}
    )
}