package com.example.yaplacaklarlistesi.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="plans")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val description : String
)
