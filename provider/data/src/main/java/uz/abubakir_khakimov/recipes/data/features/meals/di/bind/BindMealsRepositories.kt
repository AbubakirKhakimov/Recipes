package uz.abubakir_khakimov.recipes.data.features.meals.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.data.features.meals.MealsDataRepository
import uz.abubakir_khakimov.recipes.data.features.meals.repositories.MealsDataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindMealsRepositories {

    @Binds
    @Singleton
    fun bindMealsDataRepository(mealsDataRepositoryImpl: MealsDataRepositoryImpl): MealsDataRepository
}