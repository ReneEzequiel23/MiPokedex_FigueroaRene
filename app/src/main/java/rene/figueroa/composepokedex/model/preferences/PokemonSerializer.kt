package rene.figueroa.composepokedex.model.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import rene.figueroa.composepokedex.model.domain.Pokemon
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PokemonSerializer : Serializer<Pokemon> {

    override val defaultValue: Pokemon = Pokemon(
        name = "",
        number = -1,
        type = "",
        description = "d",
        height = 0f,
        weight = 0f,
        favorite = false,
        ability = "",
        image = -1
    )

    override suspend fun readFrom(input: InputStream): Pokemon {
        return try {
            Json.decodeFromString(
                Pokemon.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("No se pudo leer el Json", e)
        }
    }

    override suspend fun writeTo(
        t: Pokemon,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(Pokemon.serializer(), t)
                .encodeToByteArray()
        )
    }
}
