package com.flexath.findit.major.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexath.findit.major.domain.model.HistoryVO
import com.flexath.findit.major.domain.model.ProductVO

@Database(entities = [ProductVO::class, HistoryVO::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}