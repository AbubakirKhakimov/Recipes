package uz.abubakir_khakimov.recipes.features.home.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.features.home.mappers.CategoryMapper
import uz.abubakir_khakimov.recipes.features.home.mappers.MealMapper
import uz.abubakir_khakimov.recipes.features.home.mappers.MealToDataMapper
import uz.abubakir_khakimov.recipes.home.domain.models.Category
import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeMappersModule {

    @Provides
    @Singleton
    fun provideCategoryMapper(): EntityMapper<CategoryDataEntity, Category> =
        CategoryMapper()

    @Provides
    @Singleton
    fun provideMealMapper(): EntityMapper<MealDataEntity, Meal> =
        MealMapper()

    @Provides
    @Singleton
    fun provideMealToDataMapper(): EntityMapper<Meal, MealDataEntity> =
        MealToDataMapper()
}