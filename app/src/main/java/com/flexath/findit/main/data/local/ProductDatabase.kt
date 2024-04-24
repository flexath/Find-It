package com.flexath.findit.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.main.domain.model.ProductVO

@Database(entities = [ProductVO::class,HistoryVO::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}