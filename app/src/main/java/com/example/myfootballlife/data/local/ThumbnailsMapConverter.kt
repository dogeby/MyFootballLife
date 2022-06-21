package com.example.myfootballlife.data.local

import androidx.room.TypeConverter
import com.example.myfootballlife.data.models.youtubeapi.thumbnails.Thumbnails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** https://stackoverflow.com/questions/54978981/android-room-save-mapstring-object */
class ThumbnailsMapConverter {
    @TypeConverter
    fun stringToMap(value: String): Map<String, Thumbnails> {
        val mapType = object : TypeToken<Map<String, Thumbnails>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMap(map: Map<String, Thumbnails>): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}