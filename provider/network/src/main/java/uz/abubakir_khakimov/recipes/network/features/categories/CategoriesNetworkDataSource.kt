package uz.abubakir_khakimov.recipes.network.features.categories

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity

interface CategoriesNetworkDataSource {

    suspend fun getCategories(): Result<List<CategoryNetworkEntity>>
}