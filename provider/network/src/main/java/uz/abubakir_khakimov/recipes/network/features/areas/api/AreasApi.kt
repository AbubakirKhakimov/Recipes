package uz.abubakir_khakimov.recipes.network.features.areas.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreasNetworkEntity

interface AreasApi {

    @GET(GET_AREAS_PATH)
    suspend fun getAreas(
        @Query("a") area: String = A_QUERY_DEFAULT_VALUE
    ): Response<AreasNetworkEntity>

    companion object{

        const val GET_AREAS_PATH = "list.php"
        const val A_QUERY_DEFAULT_VALUE = "list"
    }
}