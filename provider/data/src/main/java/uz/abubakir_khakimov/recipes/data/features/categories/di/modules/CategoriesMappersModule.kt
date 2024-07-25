package uz.abubakir_khakimov.recipes.data.features.categories.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.data.features.categories.mappers.CategoryNetworkMapper
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoriesMappersModule {

    @Provides
    @Singleton
    fun provideCategoryNetworkMapper(): EntityMapper<CategoryNetworkEntity, CategoryDataEntity> = CategoryNetworkMapper()
}