package uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.dao.FavouriteMealsDao
import uz.abubakir_khakimov.recipes.local.database.initializer.MainDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouriteMealsDaoModule {

    @Provides
    @Singleton
    fun provideFavouriteMealsDao(mainDatabase: MainDatabase): FavouriteMealsDao =
        mainDatabase.favouriteMealsDao()
}