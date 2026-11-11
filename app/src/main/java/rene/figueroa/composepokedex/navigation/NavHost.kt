package rene.figueroa.composepokedex.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import rene.figueroa.composepokedex.screens.LoginScreen
import rene.figueroa.composepokedex.screens.MenuPokedexScreen
import rene.figueroa.composepokedex.view.menuPokedexScreen.PokemonDetailScreen
import rene.figueroa.composepokedex.view.menuPokedexScreen.PokemonHuntScreen
import rene.figueroa.composepokedex.view.screens.ProfileScreen
import rene.figueroa.composepokedex.viewmodel.PokemonDetailViewModel
import rene.figueroa.composepokedex.viewmodel.UserViewModel

@Composable
fun MyApp(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                viewModel = userViewModel,
                onLoginSuccess = {
                    navController.navigate(PokemonList) {
                        // Limpia Login del historial para no regresar al presionar atrás
                        popUpTo<Login> { inclusive = true }
                    }
                }
            )
        }

        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onNavigateToDetail = { id ->
                    navController.navigate(route = PokemonDetail(id))
                },
                onNavigateToHunt = {
                    navController.navigate(route = PokemonHunt)
                },
                onNavigateToProfile = {
                    navController.navigate(route = Profile)
                }
            )
        }

        composable<PokemonDetail> { backStackEntry ->
            val detail: PokemonDetail = backStackEntry.toRoute()
            PokemonDetailScreen(
                innerPadding = innerPadding,
                pokemonNumber = detail.pokemon,
                pokemonDetailViewModel = pokemonDetailViewModel
            )
        }

        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding)
        }

        composable<Profile> {
            ProfileScreen(
                innerPadding = innerPadding,
                userViewModel = userViewModel
            )
        }
    }
}
