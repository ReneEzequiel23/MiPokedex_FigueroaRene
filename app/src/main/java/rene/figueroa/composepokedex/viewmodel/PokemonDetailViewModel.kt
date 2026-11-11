package rene.figueroa.composepokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import rene.figueroa.composepokedex.model.data.CompanionPreferences
import rene.figueroa.composepokedex.model.data.getPokemonByNumber
import rene.figueroa.composepokedex.model.domain.Pokemon
import rene.figueroa.composepokedex.model.preferences.PokemonSerializer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class PokemonDetailViewModel(
    private val companionPreferences: CompanionPreferences
) : ViewModel() {

    val companion: StateFlow<Pokemon> = companionPreferences.pokemonCompanion().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PokemonSerializer.defaultValue
        )

    var selectionCompleted by mutableStateOf(false)
        private set

    fun getPokemon(number: Int): Pokemon? = runCatching { getPokemonByNumber(number) }.getOrNull()

    fun saveCompanion(number: Int) {
        val pokemon = getPokemon(number) ?: return
        selectionCompleted = false
        viewModelScope.launch {
            companionPreferences.saveCompanion(pokemon)
            selectionCompleted = true
        }

    }
}
