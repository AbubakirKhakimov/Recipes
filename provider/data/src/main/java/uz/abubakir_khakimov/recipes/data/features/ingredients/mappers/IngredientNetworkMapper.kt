package uz.abubakir_khakimov.recipes.data.features.ingredients.mappers

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.map
import uz.abubakir_khakimov.recipes.data.features.ingredients.entities.IngredientDataEntity
import uz.abubakir_khakimov.recipes.network.features.ingredients.entities.IngredientNetworkEntity

internal class IngredientNetworkMapper: EntityMapper<IngredientNetworkEntity, IngredientDataEntity> {

    override fun mapTo(entity: IngredientNetworkEntity): IngredientDataEntity =
        IngredientDataEntity(
            idIngredient = entity.idIngredient,
            strDescription = entity.strDescription,
            strIngredient = entity.strIngredient,
            strType = entity.strType
        )

    override fun mapToList(entityList: List<IngredientNetworkEntity>): List<IngredientDataEntity> =
        entityList.map { mapTo(entity = it) }

    override fun mapToResultList(
        entityResultList: Result<List<IngredientNetworkEntity>>
    ): Result<List<IngredientDataEntity>> = entityResultList.map { attendanceList ->
        mapToList(entityList = attendanceList)
    }

    override fun mapToResult(entityResult: Result<IngredientNetworkEntity>): Result<IngredientDataEntity> =
        entityResult.map { mapTo(entity = it) }
}