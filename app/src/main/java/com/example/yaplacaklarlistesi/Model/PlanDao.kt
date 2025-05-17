package com.example.yaplacaklarlistesi.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlanDao {
    @Query("SELECT * FROM plans")
    suspend fun getAll():List<Plan>
    @Insert
    suspend fun insert(plan: Plan)
    @Update
    suspend fun update(plan: Plan)
    @Delete
    suspend fun delete(plan: Plan)
}