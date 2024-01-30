package br.com.book.api.configs

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.lang.reflect.Type;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import java.time.format.DateTimeParseException

class LocalDateTypeAdapter : JsonSerializer<LocalDate?>, JsonDeserializer<LocalDate?> {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun serialize(p0: LocalDate?, p1: Type?, p2: JsonSerializationContext?): JsonPrimitive {
        if (p0 != null) {
            return JsonPrimitive(p0.format(formatter))
        }else{
            return JsonPrimitive("")
        }
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate? {
        return try {
            LocalDate.parse(json.asString, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }
}