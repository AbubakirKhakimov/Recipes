package uz.abubakir_khakimov.recipes.network.features.categories.sources

import retrofit2.HttpException
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.categories.CategoriesNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.categories.api.CategoriesApi
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity
import uz.abubakir_khakimov.recipes.network.utils.ConnectivityManagerForNetwork
import javax.inject.Inject

class CategoriesNetworkDataSourceImpl @Inject constructor(
    private val categoriesApi: CategoriesApi,
    private val connectivityManager: ConnectivityManagerForNetwork
) : CategoriesNetworkDataSource {

    override suspend fun getCategories(): Result<List<CategoryNetworkEntity>> =
        try {
            val response = categoriesApi.getCategories()
            if (response.isSuccessful) {
                Result.success(response.body()?.categories ?: throw NullPointerException())
            } else {
                connectivityManager.error(HttpException(response))
            }
        } catch (t: Throwable) {
            connectivityManager.error(t)
        }
}