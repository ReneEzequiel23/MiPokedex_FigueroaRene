package rene.figueroa.composepokedex.utilities

import androidx.compose.ui.graphics.Color
import rene.figueroa.composepokedex.ui.theme.*

fun getColorByType(type: String): Pair<Color,Color>{
    var color: Color
    var dark = true
    when{
        type.lowercase().contains("normal") -> color = Normal;
        type.lowercase().contains("electric") -> {
            color = Electric
            dark= false
        }
        type.lowercase().contains("water") -> {
            color = Water
        }
        type.lowercase().contains("fire") -> color = Fire
        type.lowercase().contains("fairy") -> {
            color = Fairy
            dark= false
        }
        type.lowercase().contains("grass") -> {
            color = Grass
        }
        type.lowercase().contains("psychic") -> {
            color = Psych
        }
        type.lowercase().contains("fighting") -> {
            color = Fight
            dark= false
        }
        type.lowercase().contains("ghost") -> color = Ghost
        type.lowercase().contains("bug") -> color = Bug
        type.lowercase().contains("poison") -> color = Poison
        type.lowercase().contains("ground") -> color = Ground
        type.lowercase().contains("rock") -> color = Rock
        type.lowercase().contains("flying") -> {
            color = Flying
            dark= false
        }
        else -> color=Normal
    }

    return Pair(color, if(dark) OffWhite else DarkGray)
}