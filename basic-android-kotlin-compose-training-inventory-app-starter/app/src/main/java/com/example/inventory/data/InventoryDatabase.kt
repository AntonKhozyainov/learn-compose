package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Item::class
    ],
    version = 1,
    exportSchema = false
)
abstract class InventoryDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        //значение Instanceвсегда актуально и одинаково для всех потоков выполнения.
        //Это означает, что изменения, сделанные одним потоком Instance, немедленно видны всем другим потокам.
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }.also {
                Instance = it
            }
        }
    }
}