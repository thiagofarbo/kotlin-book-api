package br.com.book.api.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

class JsonUtil {
    inline fun <reified T> toObject(json: String, type: Type): T {
        return try {
            Gson().fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            throw IllegalArgumentException("Error to parse JSON to type ${T::class.java}", e)
        }
    }

}

