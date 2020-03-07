package com.sergiobelda.androidtodometer.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.persistence.ProjectDao
import com.sergiobelda.androidtodometer.persistence.TaskDao
import com.sergiobelda.androidtodometer.persistence.TaskProjectDao

@Database(entities = [Project::class, Task::class], version = 1, exportSchema = false)
abstract class TodometerDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    abstract fun taskDao(): TaskDao

    abstract fun taskProjectDao(): TaskProjectDao

    companion object {
        @Volatile
        private var INSTANCE: TodometerDatabase? = null

        fun getDatabase(context: Context): TodometerDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodometerDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}