package com.example.myfootballlife.data.local

import androidx.room.TypeConverter
import com.example.myfootballlife.data.models.Kind

class KindConverter {
    @TypeConverter
    fun stringToKind(value: String) = enumValueOf<Kind>(value)

    @TypeConverter
    fun fromKind(value: Kind) = value.name
}