package uz.abubakir_khakimov.recipes.network.features.meals.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealsNetworkEntity

interface MealsApi {

    @GET(GET_MEALS_BY_FILTER_PATH)
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): Response<MealsNetworkEntity>

    @GET(GET_MEALS_BY_FILTER_PATH)
    suspend fun getMealsByArea(
        @Query("a") area: String
    ): Response<MealsNetworkEntity>

    @GET(GET_MEALS_BY_FILTER_PATH)
    suspend fun getMealsByIngredient(
        @Query("i") ingredient: String
    ): Response<MealsNetworkEntity>

    @GET(GET_RANDOM_MEAL_PATH)
    suspend fun getRandomMeal(): Response<MealsNetworkEntity>

    @GET(GET_MEAL_BY_ID_PATH)
    suspend fun getMealById(
        @Query("i") id: String
    ): Response<MealsNetworkEntity>

    @GET(GET_MEALS_BY_SEARCH)
    suspend fun getMealsBySearch(
        @Query("s") search: String
    ): Response<MealsNetworkEntity>

    companion object{

        const val GET_MEALS_BY_FILTER_PATH = "filter.php"
        const val GET_RANDOM_MEAL_PATH = "random.php"
        const val GET_MEAL_BY_ID_PATH = "lookup.php"
        const val GET_MEALS_BY_SEARCH = "search.php"
    }
}