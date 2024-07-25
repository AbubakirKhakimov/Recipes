package uz.abubakir_khakimov.recipes.network.features.meals

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealNetworkEntity

interface MealsNetworkDataSource {

    suspend fun getMealsByCategory(category: String): Result<List<MealNetworkEntity>>

    suspend fun getMealsByArea(area: String): Result<List<MealNetworkEntity>>

    suspend fun getMealsByIngredient(ingredient: String): Result<List<MealNetworkEntity>>

    suspend fun getRandomMeal(): Result<MealNetworkEntity>

    suspend fun getMealById(id: String): Result<MealNetworkEntity>

    suspend fun getMealsBySearch(search: String): Result<List<MealNetworkEntity>>
}