package uz.abubakir_khakimov.recipes.data.features.categories.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.data.features.categories.CategoriesDataRepository
import uz.abubakir_khakimov.recipes.data.features.categories.repositories.CategoriesDataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindCategoriesRepositories {

    @Binds
    @Singleton
    fun bindCategoriesDataRepository(
        categoriesDataRepositoryImpl: CategoriesDataRepositoryImpl
    ): CategoriesDataRepository
}