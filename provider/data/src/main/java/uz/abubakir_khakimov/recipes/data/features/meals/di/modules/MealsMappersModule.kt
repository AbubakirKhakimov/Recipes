package uz.abubakir_khakimov.recipes.data.features.meals.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.data.features.meals.mappers.MealDataToLocalMapper
import uz.abubakir_khakimov.recipes.data.features.meals.mappers.MealLocalMapper
import uz.abubakir_khakimov.recipes.data.features.meals.mappers.MealNetworkMapper
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealNetworkEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MealsMappersModule {

    @Provides
    @Singleton
    fun provideMealNetworkMapper(): EntityMapper<MealNetworkEntity, MealDataEntity> = MealNetworkMapper()

    @Provides
    @Singleton
    fun provideMealLocalMapper(): EntityMapper<MealLocalEntity, MealDataEntity> = MealLocalMapper()

    @Provides
    @Singleton
    fun provideMealDataToLocalMapper(): EntityMapper<MealDataEntity, MealLocalEntity> = MealDataToLocalMapper()
}