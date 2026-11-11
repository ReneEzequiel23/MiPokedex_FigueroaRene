package rene.figueroa.composepokedex.view.menuPokedexScreen

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import rene.figueroa.composepokedex.ui.theme.Purple40
import rene.figueroa.composepokedex.ui.theme.Typography
import rene.figueroa.composepokedex.view.components.PokemonRow
import rene.figueroa.composepokedex.viewmodel.PokemonViewModel

@Composable
fun PokemonHuntScreen(paddingValues: PaddingValues, viewModel: PokemonViewModel = viewModel()){

    Column(Modifier.fillMaxWidth().padding(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick ={viewModel.searchPokemon()} ) {
            Text("Buscar pokmeon en la hierva alta")
        }

        Spacer(Modifier.size(18.dp))

        viewModel.wildPokemon?.let {
            pokemon ->
            Text("Apareció un ${pokemon.name} salvaje!")
            Image(painterResource(pokemon.image),
                modifier = Modifier.height(90.dp).padding(0.dp, 15.dp),
                contentDescription = "${pokemon.name} image")

            Spacer(Modifier.size(20.dp))

            Button(onClick = {viewModel.capturePokemon()}) { }
        Text("Capturar a ${pokemon.name}!")

        }

        if (viewModel.gonePokemon){
            Text("El pokemon se escapó ", color = Color.Magenta)
        }

        if (viewModel.captured){
            Text("Pokemon capturado! ", color = Color.Green)
            Thread.sleep(2000)
            viewModel.captured = false
         }

        Spacer(Modifier.size(20.dp))
        Text("Pokemones Capturados", style = Typography.labelLarge, color = Purple40)

        LazyColumn(Modifier.padding(horizontal = 10.dp , vertical = 15.dp)) {
            items(viewModel.capturedPokemon){
                pokemon ->
                PokemonRow(pokemon)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPokemonHuntScreen(){
    PokemonHuntScreen(PaddingValues(15.dp))
}