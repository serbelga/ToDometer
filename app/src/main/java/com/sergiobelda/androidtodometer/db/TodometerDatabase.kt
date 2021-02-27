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

package com.sergiobelda.androidtodometer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sergiobelda.androidtodometer.db.dao.ProjectDao
import com.sergiobelda.androidtodometer.db.dao.TaskDao
import com.sergiobelda.androidtodometer.db.dao.TaskProjectViewDao
import com.sergiobelda.androidtodometer.db.entity.ProjectEntity
import com.sergiobelda.androidtodometer.db.entity.TaskEntity
import com.sergiobelda.androidtodometer.db.view.TaskProjectView

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
        database.execSQL("DROP VIEW IF EXISTS ProjectTaskView")
        database.execSQL("CREATE VIEW `TaskProjectView` AS SELECT t.*, p.projectName as projectName FROM task_table t LEFT JOIN project_table p ON t.taskProjectId = p.projectId ORDER BY projectId")
    }
}
