package com.flexath.findit.main.domain.use_cases

import javax.inject.Inject

data class MainUseCase @Inject constructor(
    val getAllProductsUseCase: GetAllProductUseCase,
    val getProductUseCase: GetProductUseCase,
    val getAllProductCategoriesUseCase: GetAllCategoriesUseCase,
    val getAllProductOfCategoryUseCase: GetAllProductOfCategoryUseCase,
    val searchProductsUseCase: SearchProductsUseCase,
    val getAllSearchHistoryUseCase: GetAllSearchHistoryUseCase,
    val insertHistoryUseCase: InsertHistoryUseCase
)