package rene.figueroa.composepokedex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rene.figueroa.composepokedex.R
import rene.figueroa.composepokedex.model.data.interfaces.IPokemonRepository
import rene.figueroa.composepokedex.model.domain.Pokemon

class AddPokemonViewModel(private val pokemonRepository: IPokemonRepository) : ViewModel() {
    private val pokemonsMutable = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = pokemonsMutable.asStateFlow()

    var pokemonSaved by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            pokemonsMutable.value = pokemonRepository.getPokemons()
        }
    }

    fun savePokemon(name: String, type: String, number: Int, description: String) {
        pokemonSaved = false
        errorMessage = null
        viewModelScope.launch {
            try {
                val savingPokemon = Pokemon(
                    name = name.trim(),
                    number = number,
                    type = type,
                    description = description.trim(),
                    height = 0f,
                    weight = 0f,
                    favorite = false,
                    ability = "",
                    image = R.drawable.pokeball
                )
                pokemonRepository.addPokemon(savingPokemon)
                pokemonSaved = true
                loadPokemons()
            } catch (exception: Exception) {
                errorMessage = exception.localizedMessage
                    ?: "No se pudo guardar el Pokémon."
            }
        }
    }
}
