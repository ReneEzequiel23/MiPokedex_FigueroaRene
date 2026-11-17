package rene.figueroa.composepokedex.model.data.sqllite

import android.content.ContentValues
import android.database.Cursor
import rene.figueroa.composepokedex.R
import rene.figueroa.composepokedex.model.domain.Pokemon

class PokemonMapper {
    fun getPokemon(cursor: Cursor): Pokemon {
        val number = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NUMBER))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NAME))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_DESCRIPTION))
        val type = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_TYPE))
        val favorite = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_FAVORITE)) != 0

        return Pokemon(
            name = name,
            number = number,
            type = type,
            description = description,
            height = 0f,
            weight = 0f,
            favorite = favorite,
            ability = "",
            image = R.drawable.pokeball
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues {
        val values = ContentValues().apply {
        put(Contract.Pokemons.COLUMN_NAME_NUMBER, pokemon.number)
        put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.name)
        put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.description)
        put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.type)
        put(Contract.Pokemons.COLUMN_NAME_FAVORITE, if (pokemon.favorite) 1 else 0)
    }
        return values
}
}

