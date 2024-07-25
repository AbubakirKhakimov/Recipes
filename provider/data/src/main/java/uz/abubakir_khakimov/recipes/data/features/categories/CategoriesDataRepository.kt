package uz.abubakir_khakimov.recipes.data.features.categories

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity

interface CategoriesDataRepository {

    suspend fun getCategories(): Result<List<CategoryDataEntity>>
}