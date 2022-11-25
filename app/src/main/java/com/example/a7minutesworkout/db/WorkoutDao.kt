package com.example.a7minutesworkout.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workoutEntity: WorkoutEntity)

    @Query("Select * from `history-table`")
    fun fetchAllData(): Flow<List<WorkoutEntity>>
}