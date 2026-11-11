package rene.figueroa.composepokedex.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

import rene.figueroa.composepokedex.ui.theme.Pink40
import rene.figueroa.composepokedex.ui.theme.Typography
import rene.figueroa.composepokedex.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel? = null
) {
    val user = userViewModel?.currentUser
    val nombre = user?.name ?: "Nombre Usuario"
    val mail = user?.email ?: "usuario@mail.com"

    val companionPokemon = userViewModel?.companion?.collectAsState()?.value
    val image = if (companionPokemon != null && companionPokemon.image != -1) companionPokemon.image else -1
    val pokemonName = if (companionPokemon != null && companionPokemon.name.isNotEmpty()) companionPokemon.name else "No Selected"

    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            nombre, style = Typography.titleLarge, color = Pink40
        )
        Text(
            mail, style = Typography.displayLarge, color = Pink40
        )

        Spacer(Modifier.size(50.dp))
        if (image != -1) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "imagen de pokemon"
            )
        }
        Text("Mi compañero: $pokemonName", style = Typography.titleLarge)
        if (companionPokemon != null && companionPokemon.number != -1) {
            val daysTogether = (LocalDate.now().toEpochDay() - companionPokemon.companionSinceEpochDay)
                .coerceAtLeast(0)
            val dayLabel = if (daysTogether == 1L) "día" else "días"
            Text("Es tu compañero desde hace $daysTogether $dayLabel", style = Typography.titleLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(PaddingValues(20.dp))
}
