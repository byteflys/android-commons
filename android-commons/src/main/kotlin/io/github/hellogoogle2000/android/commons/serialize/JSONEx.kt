package io.github.hellogoogle2000.android.commons.serialize

import com.google.gson.Gson

object JSONEx {

    private val gson = Gson()

    fun gson() = Gson()

    fun Any.toJson(): String {
        return gson.toJson(this)
    }

    fun <T> String.fromJson(clazz: Class<T>): T {
        return gson.fromJson(this, clazz)
    }

    fun Any?.toJsonOrNull(placeholder: String? = null): String? {
        if (this == null)
            return placeholder
        return gson.toJson(this)
    }

    fun <T> String?.fromJsonOrNull(clazz: Class<T>, placeholder: T? = null): T? {
        if (this == null)
            return placeholder
        return gson.fromJson(this, clazz)
    }
}