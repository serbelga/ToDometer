/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sergiobelda.androidtodometer.data.database.dao.ProjectDao
import com.sergiobelda.androidtodometer.data.database.dao.TaskDao
import com.sergiobelda.androidtodometer.data.database.dao.TaskProjectViewDao
import com.sergiobelda.androidtodometer.data.database.entity.ProjectEntity
import com.sergiobelda.androidtodometer.data.database.entity.TaskEntity
import com.sergiobelda.androidtodometer.data.database.view.TaskProjectView

@Database(
    entities = [ProjectEntity::class, TaskEntity::class],
    views = [TaskProjectView::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TodometerDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    abstract fun taskDao(): TaskDao

    abstract fun projectTaskViewDao(): TaskProjectViewDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("UPDATE Task SET tag = 'PINK' WHERE tag = 'DB'")
        database.execSQL("UPDATE Task SET tag = 'RED' WHERE tag = 'UI'")
        database.execSQL("UPDATE Task SET tag = 'INDIGO' WHERE tag = 'AR_VR'")
        database.execSQL("UPDATE Task SET tag = 'BLUE' WHERE tag = 'ARCH'")
        database.execSQL("UPDATE Task SET tag = 'BLUE' WHERE tag = 'WEB'")
        database.execSQL("UPDATE Task SET tag = 'TEAL' WHERE tag = 'CAM'")
        database.execSQL("UPDATE Task SET tag = 'GREEN' WHERE tag = 'MAPS'")
        database.execSQL("UPDATE Task SET tag = 'LIME' WHERE tag = 'ML'")
        database.execSQL("UPDATE Task SET tag = 'YELLOW' WHERE tag = 'IOT'")
        database.execSQL("UPDATE Task SET tag = 'AMBER' WHERE tag = 'JETPACK'")
        database.execSQL("UPDATE Task SET tag = 'ORANGE' WHERE tag = 'MEDIA'")
        database.execSQL("UPDATE Task SET tag = 'ORANGE' WHERE tag = 'KOTLIN_NATIVE'")
        database.execSQL("UPDATE Task SET tag = 'ORANGE' WHERE tag = 'KOTLIN_JS'")
        database.execSQL("UPDATE Task SET tag = 'GRAY' WHERE tag = 'OTHER'")
    }
}
