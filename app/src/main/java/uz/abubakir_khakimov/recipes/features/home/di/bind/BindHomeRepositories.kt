package uz.abubakir_khakimov.recipes.features.home.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.features.home.repositories.CategoriesRepositoryImpl
import uz.abubakir_khakimov.recipes.features.home.repositories.MealsRepositoryImpl
import uz.abubakir_khakimov.recipes.home.domain.repositories.CategoriesRepository
import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindHomeRepositories {

    @Binds
    @Singleton
    fun bindCategoriesRepository(
        categoriesRepositoryImpl: CategoriesRepositoryImpl
    ): CategoriesRepository

    @Binds
    @Singleton
    fun bindMealsRepository(
        mealsRepositoryImpl: MealsRepositoryImpl
    ): MealsRepository
}