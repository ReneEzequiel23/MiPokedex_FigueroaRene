package rene.figueroa.composepokedex.model.domain

data class PokemonState(
    val team: List<Pokemon> = emptyList(),
    val lastCapture: Pokemon? = null
)
