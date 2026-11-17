package rene.figueroa.composepokedex.model.data.interfaces

import rene.figueroa.composepokedex.model.domain.Pokemon

interface IPokemonDAO {
    suspend fun insertPokemon(pokemon: Pokemon)
    suspend fun getAllPokemons(): List<Pokemon>
    suspend fun getPokemonById(id: Integer): Pokemon?
    suspend fun getFavoritePokemons(): List<Pokemon>
    suspend fun getPokemonsByType(type: String): List<Pokemon>
    suspend fun updatePokemon(pokemon: Pokemon)

    suspend fun deletePokemon(id: Integer)
}