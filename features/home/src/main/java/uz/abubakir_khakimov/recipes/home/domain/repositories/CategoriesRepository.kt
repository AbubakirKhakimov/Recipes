package uz.abubakir_khakimov.recipes.home.domain.repositories

import uz.abubakir_khakimov.recipes.home.domain.models.Category
import uz.abubakir_khakimov.recipes.common.utils.Result

interface CategoriesRepository {

    suspend fun getCategories(): Result<List<Category>>
}