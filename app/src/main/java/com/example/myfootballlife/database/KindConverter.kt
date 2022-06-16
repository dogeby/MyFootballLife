package com.example.myfootballlife.database

import androidx.room.TypeConverter
import com.example.myfootballlife.data.Kind

class KindConverter {
    @TypeConverter
    fun stringToKind(value: String) = enumValueOf<Kind>(value)

    @TypeConverter
    fun fromKind(value: Kind) = value.name
}