package uz.abubakir_khakimov.recipes.data.features.meals

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity

interface MealsDataRepository {

    suspend fun getMealsByCategory(category: String): Result<List<MealDataEntity>>

    suspend fun getMealsByArea(area: String): Result<List<MealDataEntity>>

    suspend fun getMealsByIngredient(ingredient: String): Result<List<MealDataEntity>>

    suspend fun getRandomMeal(): Result<MealDataEntity>

    suspend fun getMealById(id: String): Result<MealDataEntity>

    suspend fun getMealsBySearch(search: String): Result<List<MealDataEntity>>

    suspend fun getFavouriteMeals(): Result<List<MealDataEntity>>

    suspend fun isFavouriteMealExist(id: String): Result<Boolean>

    suspend fun addFavouriteMeal(meal: MealDataEntity)

    suspend fun deleteFavouriteMealById(id: String)
}