package uz.abubakir_khakimov.recipes.data.features.ingredients

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.ingredients.entities.IngredientDataEntity

interface IngredientsDataRepository {

    suspend fun getIngredients(): Result<List<IngredientDataEntity>>
}