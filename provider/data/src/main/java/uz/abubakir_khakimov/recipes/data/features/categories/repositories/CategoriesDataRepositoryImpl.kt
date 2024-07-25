package uz.abubakir_khakimov.recipes.data.features.categories.repositories

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.categories.CategoriesDataRepository
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.data.features.meals.MealsDataRepository
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.FavouriteMealsLocalDataSource
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity
import uz.abubakir_khakimov.recipes.network.features.categories.CategoriesNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity
import uz.abubakir_khakimov.recipes.network.features.meals.MealsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealNetworkEntity
import javax.inject.Inject

class CategoriesDataRepositoryImpl @Inject constructor(
    private val categoriesNetworkDataSource: CategoriesNetworkDataSource,
    private val networkMapper: EntityMapper<CategoryNetworkEntity, CategoryDataEntity>,
): CategoriesDataRepository {

    override suspend fun getCategories(): Result<List<CategoryDataEntity>> =
        categoriesNetworkDataSource.getCategories().let {
            networkMapper.mapToResultList(it)
        }
}