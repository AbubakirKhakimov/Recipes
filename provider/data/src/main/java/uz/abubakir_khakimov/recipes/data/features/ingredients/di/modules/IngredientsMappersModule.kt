package uz.abubakir_khakimov.recipes.data.features.ingredients.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.ingredients.entities.IngredientDataEntity
import uz.abubakir_khakimov.recipes.data.features.ingredients.mappers.IngredientNetworkMapper
import uz.abubakir_khakimov.recipes.network.features.ingredients.entities.IngredientNetworkEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IngredientsMappersModule {

    @Provides
    @Singleton
    fun provideIngredientNetworkMapper(): EntityMapper<IngredientNetworkEntity, IngredientDataEntity> = IngredientNetworkMapper()
}