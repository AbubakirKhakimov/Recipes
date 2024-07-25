package uz.abubakir_khakimov.recipes.data.features.meals.repositories

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.meals.MealsDataRepository
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.FavouriteMealsLocalDataSource
import uz.abubakir_khakimov.recipes.local.database.features.favouriteMeals.entities.MealLocalEntity
import uz.abubakir_khakimov.recipes.network.features.meals.MealsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealNetworkEntity
import javax.inject.Inject

class MealsDataRepositoryImpl @Inject constructor(
    private val mealsNetworkDataSource: MealsNetworkDataSource,
    private val favouriteMealsLocalDataSource: FavouriteMealsLocalDataSource,
    private val networkMapper: EntityMapper<MealNetworkEntity, MealDataEntity>,
    private val localMapper: EntityMapper<MealLocalEntity, MealDataEntity>,
    private val dataToLocalMapper: EntityMapper<MealDataEntity, MealLocalEntity>,
): MealsDataRepository {

    override suspend fun getMealsByCategory(category: String): Result<List<MealDataEntity>> =
        mealsNetworkDataSource.getMealsByCategory(category = category).let {
            networkMapper.mapToResultList(it)
        }

    override suspend fun getMealsByArea(area: String): Result<List<MealDataEntity>> =
        mealsNetworkDataSource.getMealsByArea(area = area).let {
            networkMapper.mapToResultList(it)
        }

    override suspend fun getMealsByIngredient(ingredient: String): Result<List<MealDataEntity>> =
        mealsNetworkDataSource.getMealsByIngredient(ingredient = ingredient).let {
            networkMapper.mapToResultList(it)
        }

    override suspend fun getRandomMeal(): Result<MealDataEntity> =
        mealsNetworkDataSource.getRandomMeal().let { networkMapper.mapToResult(it) }

    override suspend fun getMealById(id: String): Result<MealDataEntity> =
        mealsNetworkDataSource.getMealById(id = id).let { networkMapper.mapToResult(it) }

    override suspend fun getMealsBySearch(search: String): Result<List<MealDataEntity>> =
        mealsNetworkDataSource.getMealsBySearch(search = search).let {
            networkMapper.mapToResultList(it)
        }

    override suspend fun getFavouriteMeals(): Result<List<MealDataEntity>> =
        favouriteMealsLocalDataSource.getFavouriteMeals().let { localMapper.mapToResultList(it) }

    override suspend fun isFavouriteMealExist(id: String): Result<Boolean> =
        favouriteMealsLocalDataSource.isFavouriteMealExist(id = id)

    override suspend fun addFavouriteMeal(meal: MealDataEntity) = favouriteMealsLocalDataSource
        .addFavouriteMeal(meal = dataToLocalMapper.mapTo(entity = meal))

    override suspend fun deleteFavouriteMealById(id: String) =
        favouriteMealsLocalDataSource.deleteFavouriteMealById(id = id)
}