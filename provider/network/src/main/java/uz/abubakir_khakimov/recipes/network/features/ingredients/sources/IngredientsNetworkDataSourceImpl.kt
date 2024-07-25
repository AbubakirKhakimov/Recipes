package uz.abubakir_khakimov.recipes.network.features.ingredients.sources

import retrofit2.HttpException
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.ingredients.IngredientsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.ingredients.api.IngredientsApi
import uz.abubakir_khakimov.recipes.network.features.ingredients.entities.IngredientNetworkEntity
import uz.abubakir_khakimov.recipes.network.utils.ConnectivityManagerForNetwork
import javax.inject.Inject

class IngredientsNetworkDataSourceImpl @Inject constructor(
    private val ingredientsApi: IngredientsApi,
    private val connectivityManager: ConnectivityManagerForNetwork
) : IngredientsNetworkDataSource {

    override suspend fun getIngredients(): Result<List<IngredientNetworkEntity>> =
        try {
            val response = ingredientsApi.getIngredients()
            if (response.isSuccessful) {
                Result.success(response.body()?.meals ?: throw NullPointerException())
            } else {
                connectivityManager.error(HttpException(response))
            }
        } catch (t: Throwable) {
            connectivityManager.error(t)
        }
}