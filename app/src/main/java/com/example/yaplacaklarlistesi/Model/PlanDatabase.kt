package com.example.yaplacaklarlistesi.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Plan::class], version = 1)
abstract class PlanDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao

    companion object{
        @Volatile private var INSTANCE : PlanDatabase? = null

        fun getDataBase(context: Context):PlanDatabase{
            return INSTANCE ?: synchronized(this){
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    PlanDatabase::class.java,
                    "plan_database"
                ).build()
                INSTANCE = inst
                inst
            }
        }
    }
}