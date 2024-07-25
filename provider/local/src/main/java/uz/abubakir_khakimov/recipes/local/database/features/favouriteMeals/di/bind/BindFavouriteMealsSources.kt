package uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.FavouriteMealsLocalDataSource
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.sources.FavouriteMealsLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindFavouriteMealsSources {

    @Binds
    @Singleton
    fun bindFavouriteMealsLocalDataSource(
        favouriteMealsLocalDataSourceImpl: FavouriteMealsLocalDataSourceImpl
    ): FavouriteMealsLocalDataSource
}