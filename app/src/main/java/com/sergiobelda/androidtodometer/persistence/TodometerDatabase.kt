package com.sergiobelda.androidtodometer.persistence

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.TypeConverters
import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskView
import com.sergiobelda.androidtodometer.model.Task

@Database(
    entities = [Project::class, Task::class],
    views = [ProjectTaskView::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TodometerDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    abstract fun taskDao(): TaskDao

    abstract fun projectTaskViewDao(): ProjectTaskViewDao

    /*
    SINGLETON
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
    */
}