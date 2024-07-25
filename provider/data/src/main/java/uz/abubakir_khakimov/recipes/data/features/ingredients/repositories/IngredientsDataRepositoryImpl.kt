package uz.abubakir_khakimov.recipes.data.features.ingredients.repositories

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.ingredients.IngredientsDataRepository
import uz.abubakir_khakimov.recipes.data.features.ingredients.entities.IngredientDataEntity
import uz.abubakir_khakimov.recipes.network.features.ingredients.IngredientsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.ingredients.entities.IngredientNetworkEntity
import javax.inject.Inject

class IngredientsDataRepositoryImpl @Inject constructor(
    private val ingredientsNetworkDataSource: IngredientsNetworkDataSource,
    private val networkMapper: EntityMapper<IngredientNetworkEntity, IngredientDataEntity>,
): IngredientsDataRepository {

    override suspend fun getIngredients(): Result<List<IngredientDataEntity>> =
        ingredientsNetworkDataSource.getIngredients().let {
            networkMapper.mapToResultList(it)
        }
}