package uz.abubakir_khakimov.recipes.network.features.ingredients

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.ingredients.entities.IngredientNetworkEntity

interface IngredientsNetworkDataSource {

    suspend fun getIngredients(): Result<List<IngredientNetworkEntity>>
}