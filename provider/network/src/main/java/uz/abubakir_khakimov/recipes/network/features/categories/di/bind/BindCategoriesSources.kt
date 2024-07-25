package uz.abubakir_khakimov.recipes.network.features.categories.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.network.features.categories.CategoriesNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.categories.sources.CategoriesNetworkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindCategoriesSources {

    @Binds
    @Singleton
    fun bindCategoriesNetworkDataSource(
        categoriesNetworkDataSourceImpl: CategoriesNetworkDataSourceImpl
    ): CategoriesNetworkDataSource
}