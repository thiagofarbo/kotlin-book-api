package br.com.book.api.util

import br.com.book.api.configs.LocalDateTypeAdapter
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type
import java.time.LocalDate


class JsonUtil {
    inline fun <reified T> toObject(json: String, type: Type): T {
        return try {
            //Adapter created for deserialize LocalDate field
            val gson = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                .create()
            gson.fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            throw IllegalArgumentException("Error to parse JSON to type ${T::class.java}", e)
        }
    }
}

