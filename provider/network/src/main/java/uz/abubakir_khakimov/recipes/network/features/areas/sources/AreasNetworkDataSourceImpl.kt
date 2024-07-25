package uz.abubakir_khakimov.recipes.network.features.areas.sources

import retrofit2.HttpException
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.areas.AreasNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.areas.api.AreasApi
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreaNetworkEntity
import uz.abubakir_khakimov.recipes.network.utils.ConnectivityManagerForNetwork
import javax.inject.Inject

class AreasNetworkDataSourceImpl @Inject constructor(
    private val areasApi: AreasApi,
    private val connectivityManager: ConnectivityManagerForNetwork
) : AreasNetworkDataSource {

    override suspend fun getAreas(): Result<List<AreaNetworkEntity>> =
        try {
            val response = areasApi.getAreas()
            if (response.isSuccessful) {
                Result.success(response.body()?.meals ?: throw NullPointerException())
            } else {
                connectivityManager.error(HttpException(response))
            }
        } catch (t: Throwable) {
            connectivityManager.error(t)
        }
}