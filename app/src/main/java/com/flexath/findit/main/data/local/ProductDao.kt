package com.flexath.findit.main.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.main.domain.model.ProductVO

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(productList: List<ProductVO>)

    @Query("DELETE FROM product_table")
    suspend fun deleteProductList()

    @Query("SELECT * FROM product_table")
    suspend fun getProductList(): List<ProductVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(query: HistoryVO)

    @Query("SELECT * FROM history_table ORDER BY id DESC LIMIT 20")
    suspend fun getAllSearchHistory(): List<HistoryVO>
}