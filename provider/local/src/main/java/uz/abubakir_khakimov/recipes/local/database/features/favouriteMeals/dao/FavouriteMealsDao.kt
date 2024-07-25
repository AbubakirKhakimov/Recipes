package uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity

@Dao
interface FavouriteMealsDao {

    @Query("SELECT * FROM favourite_meals_table")
    suspend fun getFavouriteMeals(): List<MealLocalEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM favourite_meals_table WHERE id_meal = :id)")
    suspend fun isFavouriteMealExist(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteMeal(meal: MealLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllFavouriteMeals(meals: List<MealLocalEntity>)

    @Query("DELETE FROM favourite_meals_table WHERE id_meal = :id")
    suspend fun deleteFavouriteMealById(id: String)

    @Query("DELETE FROM favourite_meals_table")
    suspend fun clearFavouriteMealsTable()
}