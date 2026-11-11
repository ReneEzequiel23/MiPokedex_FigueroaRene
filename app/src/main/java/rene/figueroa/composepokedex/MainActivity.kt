package rene.figueroa.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import rene.figueroa.composepokedex.model.data.CompanionPreferences
import rene.figueroa.composepokedex.model.data.Users
import rene.figueroa.composepokedex.navigation.MyApp
import rene.figueroa.composepokedex.ui.theme.PokedexListTheme
import rene.figueroa.composepokedex.viewmodel.PokemonDetailViewModel
import rene.figueroa.composepokedex.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexListTheme {
                val context = LocalContext.current
                val users = remember { Users(context) }
                val companionPreferences = remember { CompanionPreferences(context) }
                val userViewModel = remember { UserViewModel(users, companionPreferences) }
                val pokemonDetailViewModel = remember { PokemonDetailViewModel(companionPreferences) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        innerPadding = innerPadding,
                        userViewModel = userViewModel,
                        pokemonDetailViewModel = pokemonDetailViewModel
                    )
                }
            }
        }
    }
}
