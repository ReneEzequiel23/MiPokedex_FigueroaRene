package rene.figueroa.composepokedex.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import rene.figueroa.composepokedex.model.data.pokemonList
import rene.figueroa.composepokedex.model.domain.Pokemon

class PokemonViewModel: ViewModel() {


    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemon by mutableStateOf(listOf<Pokemon>())
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    fun searchPokemon(){
        wildPokemon = pokemonList.random()
    }


    fun capturePokemon(){
        wildPokemon?.let {
            val isCaptured = (1..2).random()
            if (isCaptured == 1){
                capturedPokemon = capturedPokemon + it
                gonePokemon = false
                wildPokemon = null
            }else{
                gonePokemon = true
                wildPokemon = null
            }
        }
    }
}