package rene.figueroa.composepokedex.model.data.sqllite

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rene.figueroa.composepokedex.model.data.interfaces.IPokemonDAO
import rene.figueroa.composepokedex.model.domain.Pokemon

class PokemonDAO(private val helper: DatabaseHelper) : IPokemonDAO {
    private val mapper = PokemonMapper()

    override suspend fun insertPokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.beginTransaction()
            try {
                val numberExists = db.query(
                    Contract.Pokemons.TABLE_NAME,
                    arrayOf(Contract.Pokemons.COLUMN_ID),
                    "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                    arrayOf(pokemon.number.toString()),
                    null,
                    null,
                    null
                ).use { cursor -> cursor.moveToFirst() }

                if (numberExists) {
                    throw SQLiteConstraintException(
                        "Ya existe un Pokémon con el número ${pokemon.number}."
                    )
                }

                db.insertOrThrow(
                    Contract.Pokemons.TABLE_NAME,
                    null,
                    mapper.getContentValues(pokemon)
                )
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
                db.close()
            }
        }
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME}"

            val cursor = db.rawQuery(query, null)
            cursor.use {
                    c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(cursor))
                    }
                }
            }
        }
    }

    override suspend fun getPokemonById(id: Integer): Pokemon?  {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val pokemon = db.query(
                Contract.Pokemons.TABLE_NAME,
                null,
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                arrayOf(id.toString()),
                null,
                null,
                null
            ).use { cursor -> if (cursor.moveToFirst()) mapper.getPokemon(cursor) else null }
            db.close()
            pokemon
        }
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val result = mutableListOf<Pokemon>()
            db.query(
                Contract.Pokemons.TABLE_NAME,
                null,
                "${Contract.Pokemons.COLUMN_NAME_FAVORITE} = ?",
                arrayOf("1"),
                null,
                null,
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} ASC"
            ).use { cursor ->
                while (cursor.moveToNext()) result += mapper.getPokemon(cursor)
            }
            db.close()
            result
        }
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val result = mutableListOf<Pokemon>()
            db.query(
                Contract.Pokemons.TABLE_NAME,
                null,
                "${Contract.Pokemons.COLUMN_NAME_TYPE} = ?",
                arrayOf(type),
                null,
                null,
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} ASC"
            ).use { cursor ->
                while (cursor.moveToNext()) result += mapper.getPokemon(cursor)
            }
            db.close()
            result
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.update(
                Contract.Pokemons.TABLE_NAME,
                mapper.getContentValues(pokemon),
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                arrayOf(pokemon.number.toString())
            )
            db.close()
        }
    }

    override suspend fun deletePokemon(id: Integer) {
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.delete(
                Contract.Pokemons.TABLE_NAME,
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                arrayOf(id.toString())
            )
            db.close()
        }
    }
}
