package uz.abubakir_khakimov.recipes.data.features.ingredients.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.data.features.ingredients.IngredientsDataRepository
import uz.abubakir_khakimov.recipes.data.features.ingredients.repositories.IngredientsDataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindIngredientsRepositories {

    @Binds
    @Singleton
    fun bindIngredientsDataRepository(
        ingredientsDataRepositoryImpl: IngredientsDataRepositoryImpl
    ): IngredientsDataRepository
}