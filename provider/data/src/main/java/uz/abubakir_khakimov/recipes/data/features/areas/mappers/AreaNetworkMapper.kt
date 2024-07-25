package uz.abubakir_khakimov.recipes.data.features.areas.mappers

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.map
import uz.abubakir_khakimov.recipes.data.features.areas.entities.AreaDataEntity
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreaNetworkEntity

internal class AreaNetworkMapper: EntityMapper<AreaNetworkEntity, AreaDataEntity> {

    override fun mapTo(entity: AreaNetworkEntity): AreaDataEntity =
        AreaDataEntity(
            strArea = entity.strArea
        )

    override fun mapToList(entityList: List<AreaNetworkEntity>): List<AreaDataEntity> =
        entityList.map { mapTo(entity = it) }

    override fun mapToResultList(
        entityResultList: Result<List<AreaNetworkEntity>>
    ): Result<List<AreaDataEntity>> = entityResultList.map { attendanceList ->
        mapToList(entityList = attendanceList)
    }

    override fun mapToResult(entityResult: Result<AreaNetworkEntity>): Result<AreaDataEntity> =
        entityResult.map { mapTo(entity = it) }
}