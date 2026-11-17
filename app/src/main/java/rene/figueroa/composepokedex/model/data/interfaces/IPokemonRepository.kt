package rene.figueroa.composepokedex.model.data.interfaces

import rene.figueroa.composepokedex.model.domain.Pokemon

interface IPokemonRepository {
    suspend fun addPokemon(pokemon: Pokemon)
    suspend fun getPokemons(): List<Pokemon>
    suspend fun getFavoritePokemons(): List<Pokemon>
    suspend fun getPokemonsByType(type: String): List<Pokemon>
    suspend fun updatePokemon(pokemon: Pokemon)
    suspend fun deletePokemon(id: Integer)
}
