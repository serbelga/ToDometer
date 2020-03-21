package com.sergiobelda.androidtodometer.database

import androidx.room.TypeConverter
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.model.Tag

// NOTE: each conversion must have two functions to convert A to B and B to A
// i.e. Tag to Int and Int to Tag
class Converters {
    @TypeConverter
    fun toInt(tag: Tag?): Int? {
        return tag?.ordinal
    }

    @TypeConverter
    fun toTag(ordinal: Int): Tag? {
        return enumValues<Tag>()[ordinal]
    }

    @TypeConverter
    fun toInt(taskState: TaskState?): Int? {
        return taskState?.ordinal
    }

    @TypeConverter
    fun toTaskState(ordinal: Int): TaskState? {
        return enumValues<TaskState>()[ordinal]
    }
}