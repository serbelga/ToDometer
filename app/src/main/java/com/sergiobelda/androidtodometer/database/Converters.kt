package com.sergiobelda.androidtodometer.database

import androidx.room.TypeConverter
import com.sergiobelda.androidtodometer.model.Tag

// NOTE: each conversion must have two functions to convert A to B and B to A
// i.e. Tag to Int and Int to Tag
class Converters {
    @TypeConverter
    fun toInt(tag: Tag?): Int? {
        return tag?.ordinal
    }

    @TypeConverter
    fun toTag(ordinal: Int?): Tag? {
        return enumValues<Tag>()[ordinal!!]
    }
}