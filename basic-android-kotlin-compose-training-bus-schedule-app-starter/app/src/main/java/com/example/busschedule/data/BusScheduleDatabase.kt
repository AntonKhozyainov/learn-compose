package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        BusSchedule::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BusScheduleDatabase: RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    companion object {

        //значение Instanceвсегда актуально и одинаково для всех потоков выполнения.
        //Это означает, что изменения, сделанные одним потоком Instance, немедленно видны всем другим потокам.
        @Volatile
        private var Instance: BusScheduleDatabase? = null

        fun getDatabase(context: Context): BusScheduleDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BusScheduleDatabase::class.java, "bus_schedule_database")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }.also {
                Instance = it
            }
        }
    }
}