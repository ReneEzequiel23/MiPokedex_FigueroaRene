package rene.figueroa.composepokedex.model.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lastCapture: Pokemon? = null
)
