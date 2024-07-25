package uz.abubakir_khakimov.recipes.network.features.categories.api

import retrofit2.Response
import retrofit2.http.GET
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoriesNetworkEntity

interface CategoriesApi {

    @GET(GET_CATEGORIES_PATH)
    suspend fun getCategories(): Response<CategoriesNetworkEntity>

    companion object{

        const val GET_CATEGORIES_PATH = "categories.php"
    }
}