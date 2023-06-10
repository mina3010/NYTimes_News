package com.minamagid.thechallenge.domain.model.homeResponses

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListStringConverter {
    @TypeConverter
    fun fromListString(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toListString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListMedia(value: List<Media>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toListMedia(value: String): List<Media> {
        val listType = object : TypeToken<List<Media>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
