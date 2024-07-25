package uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity

interface FavouriteMealsLocalDataSource {

    suspend fun getFavouriteMeals(): Result<List<MealLocalEntity>>

    suspend fun isFavouriteMealExist(id: String): Result<Boolean>

    suspend fun addFavouriteMeal(meal: MealLocalEntity)

    suspend fun addAllFavouriteMeals(meals: List<MealLocalEntity>)

    suspend fun deleteFavouriteMealById(id: String)

    suspend fun clearFavouriteMealsTable()
}