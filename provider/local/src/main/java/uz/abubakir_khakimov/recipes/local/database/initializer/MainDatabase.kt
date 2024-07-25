package uz.abubakir_khakimov.recipes.local.database.initializer

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.dao.FavouriteMealsDao
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity

@Database(
    entities = [
        MealLocalEntity::class,
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun favouriteMealsDao(): FavouriteMealsDao
}