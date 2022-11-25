package com.example.a7minutesworkout.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-table")
data class WorkoutEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String? = "",
    val time: String? = ""
)
