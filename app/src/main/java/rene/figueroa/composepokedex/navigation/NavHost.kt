package rene.figueroa.composepokedex.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rene.figueroa.composepokedex.screens.MenuPokedexScreen
import rene.figueroa.composepokedex.view.menuPokedexScreen.PokemonDetailScreen


@Composable
fun MyApp(innerPadding: PaddingValues){
    val navController = rememberNavController()
    NavHost(navController, startDestination = PokemonList){
        composable<PokemonList> {
            MenuPokedexScreen(innerPadding, onNavigateToDetail = {pokemon -> navController.navigate(
                PokemonDetail(pokemon))})
        }
        composable<PokemonDetail>() {
            val pokemon = it.arguments?.getInt("pokemon") ?: -1
            PokemonDetailScreen(innerPadding, pokemon)
        }
    }
}