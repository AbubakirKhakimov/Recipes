package uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.sources

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.FavouriteMealsLocalDataSource
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.dao.FavouriteMealsDao
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity
import javax.inject.Inject

class FavouriteMealsLocalDataSourceImpl @Inject constructor(
    private val favouriteMealsDao: FavouriteMealsDao
) : FavouriteMealsLocalDataSource {

    override suspend fun getFavouriteMeals(): Result<List<MealLocalEntity>> =
        try {
            val data = favouriteMealsDao.getFavouriteMeals()
            Result.success(data)
        } catch (t: Throwable) {
            Result.error(t)
        }

    override suspend fun isFavouriteMealExist(id: String): Result<Boolean> =
        try {
            val data = favouriteMealsDao.isFavouriteMealExist(id = id)
            Result.success(data)
        } catch (t: Throwable) {
            Result.error(t)
        }

    override suspend fun addFavouriteMeal(meal: MealLocalEntity) {
        favouriteMealsDao.addFavouriteMeal(meal = meal)
    }

    override suspend fun addAllFavouriteMeals(meals: List<MealLocalEntity>) {
        favouriteMealsDao.addAllFavouriteMeals(meals = meals)
    }

    override suspend fun deleteFavouriteMealById(id: String) {
        favouriteMealsDao.deleteFavouriteMealById(id = id)
    }

    override suspend fun clearFavouriteMealsTable() {
        favouriteMealsDao.clearFavouriteMealsTable()
    }
}