package rene.figueroa.composepokedex.utilities

import androidx.compose.ui.graphics.Color
import rene.figueroa.composepokedex.ui.theme.*

fun getColorByType(type: String): Pair<Color, Color> {
    val mainType = type.split("/")[0].trim()

    val backgroundColor = when (mainType) {
        "Electric" -> Electric
        "Grass" -> Grass
        "Fire" -> Fire
        "Water" -> Water
        "Normal" -> Normal
        "Bug" -> Bug
        "Poison" -> Poison
        "Ground" -> Ground
        "Rock" -> Rock
        "Flying" -> Flying
        "Fighting", "Fight" -> Fight
        "Psychic", "Psych" -> Psychic
        "Ghost" -> Ghost
        "Dragon" -> Dragon
        "Dark" -> Dark
        "Ice" -> Ice
        "Fairy" -> Fairy
        else -> DarkGray
    }

    val textColor = when (mainType) {
        "Normal", "Water", "Fire", "Psychic", "Psych", "Ghost", "Fairy", "Ice" -> OffWhite
        else -> DarkGray
    }

    return Pair(backgroundColor, textColor)
}