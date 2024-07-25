package uz.abubakir_khakimov.recipes.network.features.meals.sources

import retrofit2.HttpException
import retrofit2.Response
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.map
import uz.abubakir_khakimov.recipes.network.features.meals.MealsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.meals.api.MealsApi
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealNetworkEntity
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealsNetworkEntity
import uz.abubakir_khakimov.recipes.network.utils.ConnectivityManagerForNetwork
import javax.inject.Inject

class MealsNetworkDataSourceImpl @Inject constructor(
    private val mealsApi: MealsApi,
    private val connectivityManager: ConnectivityManagerForNetwork
) : MealsNetworkDataSource {

    override suspend fun getMealsByCategory(category: String): Result<List<MealNetworkEntity>> =
        mealsApi.getMealsByCategory(category = category).getResult()

    override suspend fun getMealsByArea(area: String): Result<List<MealNetworkEntity>> =
        mealsApi.getMealsByArea(area = area).getResult()

    override suspend fun getMealsByIngredient(ingredient: String): Result<List<MealNetworkEntity>> =
        mealsApi.getMealsByIngredient(ingredient = ingredient).getResult()

    override suspend fun getRandomMeal(): Result<MealNetworkEntity> =
        mealsApi.getRandomMeal().getResult().map { it.first() }

    override suspend fun getMealById(id: String): Result<MealNetworkEntity> =
        mealsApi.getMealById(id = id).getResult().map { it.first() }

    override suspend fun getMealsBySearch(search: String): Result<List<MealNetworkEntity>> =
        mealsApi.getMealsBySearch(search = search).getResult()

    private fun Response<MealsNetworkEntity>.getResult(): Result<List<MealNetworkEntity>> =
        try {
            if (isSuccessful) {
                Result.success(body()?.meals ?: throw NullPointerException())
            } else {
                connectivityManager.error(HttpException(this))
            }
        } catch (t: Throwable) {
            connectivityManager.error(t)
        }
}