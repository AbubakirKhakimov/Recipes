package uz.abubakir_khakimov.recipes.network.features.ingredients.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abubakir_khakimov.recipes.network.features.ingredients.entities.IngredientsNetworkEntity

interface IngredientsApi {

    @GET(GET_INGREDIENTS_PATH)
    suspend fun getIngredients(
        @Query("i") ingredient: String = I_QUERY_DEFAULT_VALUE
    ): Response<IngredientsNetworkEntity>

    companion object{

        const val GET_INGREDIENTS_PATH = "list.php"
        const val I_QUERY_DEFAULT_VALUE = "list"
    }
}